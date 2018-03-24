package tgtools.spirngbootweb.common.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 访问地址如：http://ip:port/${context-path}/quartz/explorer/manage/resource/quartz.html
 * 建表sql：tgtools.quartz.explorer.jar  -> tgtools/quartz/explorer/db/sql.sql
 */
@Configuration
@MapperScan(basePackages = {"tgtools.quartz.explorer.dao"}, sqlSessionFactoryRef = "quartzSqlSessionFactory")
public class QuartzConfigration  extends tgtools.quartz.explorer.config.QuartzConfigration{

//    数据库类型
//    @Override
//    protected String getDataBaseType() {
//        return "dm6";
//    }
}
