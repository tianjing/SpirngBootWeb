package com.github.tianjing.test.db.webapp.mybatis.db1;


import org.apache.ibatis.annotations.SelectProvider;
import tgtools.data.DataTable;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 8:54
 */
public interface MyUserDao extends Mapper<MyUserDO> {
    @SelectProvider(type=MyUserDO.class,method = "pageSql")
    List<MyUserDO> pageSql(int pPageIndex, int pPageSize);

    List<MyUserDO> lista();
    @SelectProvider(type=MyUserDO.class,method = "pageSql")
    DataTable selectTable(int pPageIndex, int pPageSize);
}
