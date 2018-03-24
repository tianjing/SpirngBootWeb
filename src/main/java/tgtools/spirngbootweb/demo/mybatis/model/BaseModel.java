package tgtools.spirngbootweb.demo.mybatis.model;


import tgtools.spirngbootweb.util.ModelHelper;
import tgtools.util.GUID;
import tgtools.util.StringUtil;
import tgtools.web.util.PageSqlUtil;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 18:47
 */
public class BaseModel {
    @Id
    @Column(name="ID_")
    private String mId;
    @Column(name="REV_")
    private Long mRev;

    public String getId() {
        return mId;
    }

    public void setId(String pId) {
        mId = pId;
    }

    public Long getRev() {
        return mRev;
    }

    public void setRev(Long pRev) {
        mRev = pRev;
    }
    /**
     *
     * @param pPageIndex
     * @param pPageSize
     * @return
     */
    public String pageSql(int pPageIndex, int pPageSize)
    {
        String tablename= ModelHelper.getTableName(this.getClass());
        String sql="select * from ${tablename} order by rev_";
        sql= StringUtil.replace(sql,"${tablename}",tablename);
        sql = PageSqlUtil.getPageDataSQL(sql,String.valueOf(pPageIndex),String.valueOf(pPageSize));
        return sql;
    }
    /**
     *
     * @return
     */
    public String treeSql()
    {
        String tablename= ModelHelper.getTableName(this.getClass());
        String sql="select * from ${tablename} order by rev_";

        return sql;
    }

    /**
     * 初始化新建信息
     */
    public void initNew()
    {
        if(StringUtil.isNullOrEmpty(getId())) {
            setId(GUID.newGUID());
        }
        if(null==getRev()||getRev()<1) {
            setRev(System.currentTimeMillis());
        }
    }
}
