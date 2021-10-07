package com.github.tianjing.tgtools.web.security.autoconfigure.handler;


import com.github.tianjing.tgtools.web.security.autoconfigure.util.enumext.TeamEnumHelper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author 田径
 * @date 2020-04-21 11:22
 * @desc
 **/
public class IntValueEnumTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {
    private Class<E> type;
    private Map<Object, Enum> values;
    public IntValueEnumTypeHandler()
    {}
    public IntValueEnumTypeHandler(Class<E> pType) {
        this.type = pType;
        values = TeamEnumHelper.getValueEnumMap(pType);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, getValue(parameter));
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Object vValue = rs.getObject(columnName);
        if (null == vValue) {
            return null;
        }

        if (vValue instanceof Integer) {
            return get(type, (Integer) vValue);
        }
        throw new SQLException("CallableStatement 获取值失败；类型不为Integer，columnIndex：" + columnName);

    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Object vValue = rs.getObject(columnIndex);
        if (null == vValue) {
            return null;
        }

        if (vValue instanceof Integer) {
            return get(type, (Integer) vValue);
        }
        throw new SQLException("CallableStatement 获取值失败；类型不为Integer，columnIndex：" + columnIndex);

    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Object vValue = cs.getObject(columnIndex);
        if (null == vValue) {
            return null;
        }

        if (vValue instanceof Integer) {
            return get(type, (Integer) vValue);
        }
        throw new SQLException("CallableStatement 获取值失败；类型不为Integer，columnIndex：" + columnIndex);
    }


    private <E extends Enum<E>> E get(Class<E> pType, Integer pValue) throws SQLException {
        return (E)values.get(pValue);
    }



    private <E extends Enum<E>> E[] getEnumValues(Class<E> pType) throws SQLException {
        try {
            Method vMethod = pType.getDeclaredMethod("values");
            return (E[]) vMethod.invoke(pType);
        } catch (Exception e) {
            throw new SQLException(pType.getSimpleName() + "枚举取所有值异常:" + e.toString());
        }
    }

    private <E extends Enum<E>> int getValue(E vParameter) throws SQLException {
        try {
            Method vMethod = vParameter.getClass().getDeclaredMethod("getValue");
            return (int) vMethod.invoke(vParameter);
        } catch (Exception e) {
            throw new SQLException(vParameter.getClass().getSimpleName() + "枚举取值异常:" + e.toString());
        }
    }

}
