package com.github.tianjing.tgtools.web.security.autoconfigure.util;

import tgtools.db.IDataAccess;
import tgtools.exceptions.APPErrorException;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;

/**
 * DataTable 帮助类
 *
 * @author
 */
public class TeamDataAccessHelper {

    /**
     * 获取sql 中的大字段 数据内容
     * 注意 sql 中必须返回一个字段且字段类型 CLOB BLOB 类型之一
     *
     * @param pDataAccess
     * @param pSql
     * @return
     * @throws APPErrorException
     */
    public static InputStream getOnlyOneBigData(IDataAccess pDataAccess, String pSql) throws APPErrorException {
       return getOnlyOneBigData(pDataAccess.getDataSource(), pSql);
    }

    public static InputStream getOnlyOneBigData(DataSource pDataSource, String pSql) throws APPErrorException {
        if (null == pDataSource) {
            throw new APPErrorException("DataSource 为null");
        }

        Connection vConnection = null;
        ResultSet vResultSet = null;
        Statement vStatement = null;
        try {

            vConnection = pDataSource.getConnection();
            vStatement = vConnection.createStatement();
            vResultSet = vStatement.executeQuery(pSql);
            if (vResultSet.getMetaData().getColumnCount() < 1) {
                throw new APPErrorException("返回列数不能小于1");
            }
            if (vResultSet.getMetaData().getColumnCount() > 1) {
                throw new APPErrorException("返回列数不能大于1");
            }
            if (vResultSet.getMetaData().getColumnType(1) != Types.BLOB &&
                    vResultSet.getMetaData().getColumnType(1) != Types.CLOB) {
                throw new APPErrorException("不支持的类型：" + vResultSet.getMetaData().getColumnType(1));
            }
            if (!vResultSet.next()) {
                return null;
            }
            Object vData = vResultSet.getObject(1);
            if (null == vData) {
                return null;
            }
            if (vData instanceof Clob) {
                return ((Clob) vData).getAsciiStream();
            } else if (vData instanceof Blob) {
                return ((Blob) vData).getBinaryStream();
            }
            throw new APPErrorException("不支持的返回类型：" + vData.getClass());

        } catch (Exception e) {
            throw new APPErrorException("sql执行失败：" + pSql, e);
        } finally {
            if (null != vResultSet) {
                try {
                    vResultSet.close();
                } catch (SQLException throwables) {
                }
            }
            if (null != vStatement) {
                try {
                    vStatement.close();
                } catch (SQLException throwables) {
                }
            }
            if (null != vConnection) {
                try {
                    vConnection.close();
                } catch (SQLException throwables) {
                }
            }
        }


    }


}