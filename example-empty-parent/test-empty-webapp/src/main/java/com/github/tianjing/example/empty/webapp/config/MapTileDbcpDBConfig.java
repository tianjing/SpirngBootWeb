package com.github.tianjing.example.empty.webapp.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tgtools.web.db.TransactionDataAccess;
import tgtools.web.rests.map.MapTiles;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * sqlite 地图切片数据源
 * @author 田径
 * @Title
 * @Description
 * @date 13:27
 */
@Configuration
public class MapTileDbcpDBConfig {
    private Logger logger = LoggerFactory.getLogger(MapTileDbcpDBConfig.class);
    @Bean(destroyMethod = "close")
    public TransactionDataAccess mapTileDataAccess(@Qualifier("mapTileDataSource") DataSource dataSource) {
        TransactionDataAccess localDataAccess = new TransactionDataAccess();
        try {
            localDataAccess.init(dataSource);
            tgtools.db.DataBaseFactory.add("MapTileDATAACCESS", localDataAccess);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localDataAccess;
    }

    @Bean(initMethod = "init", destroyMethod = "close")   //声明其为Bean实例
    public DataSource mapTileDataSource() {
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl("jdbc:sqlite::resource:MapTile/Data.gmdb");
        datasource.setDriverClassName("org.sqlite.JDBC");

        //configuration
        datasource.setInitialSize(1);
        datasource.setMinIdle(3);
        datasource.setMaxActive(20);
        datasource.setMaxWait(60000);
        datasource.setTimeBetweenEvictionRunsMillis(60000);
        datasource.setMinEvictableIdleTimeMillis(30000);
        datasource.setValidationQuery("select 1");
        datasource.setTestWhileIdle(true);
        datasource.setTestOnBorrow(false);
        datasource.setTestOnReturn(false);
        datasource.setPoolPreparedStatements(true);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(20);
        try {
            datasource.setFilters("stat,slf4j");
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }

        return datasource;
    }

    /**
     * 开启地图 url
     * @return
     */
    @Bean
    public MapTiles getMapTiles()
    {
        return new MapTiles();
    }
}
