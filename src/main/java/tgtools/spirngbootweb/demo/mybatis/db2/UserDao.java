package tgtools.spirngbootweb.demo.mybatis.db2;


import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 8:54
 */

public interface UserDao extends Mapper<UserDo> {

    List<UserDo> lista();
}
