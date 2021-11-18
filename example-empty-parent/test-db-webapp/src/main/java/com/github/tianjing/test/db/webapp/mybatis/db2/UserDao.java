package com.github.tianjing.test.db.webapp.mybatis.db2;


import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 8:54
 */

public interface UserDao extends Mapper<UserDO> {

    List<UserDO> lista();
}
