package tgtools.spirngbootweb.demo.mybatis.db1;


import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 8:54
 */
public interface MyUserDao extends Mapper<MyUserDo> {
    @SelectProvider(type=MyUserDo.class,method = "pageSql")
    List<MyUserDo> pageSql(int pPageIndex, int pPageSize);

    List<MyUserDo> lista();
}
