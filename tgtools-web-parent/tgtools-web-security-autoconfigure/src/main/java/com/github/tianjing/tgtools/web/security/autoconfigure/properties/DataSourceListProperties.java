package com.github.tianjing.tgtools.web.security.autoconfigure.properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import tgtools.db.AbstractDataAccess;
import tgtools.db.DataSourceDataAccess;
import tgtools.exceptions.APPErrorException;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author 田径
 * @date 2019-09-02 20:40
 * @desc
 **/
public class DataSourceListProperties {

    @Autowired
    private ApplicationContext applicationContext;

    private boolean addDataBaseFactory = false;
    private boolean addSpringBean = false;

    private List<DataSourceProperties> configs;


    public DataSourceListProperties() {
    }

    public DataSourceListProperties(boolean pAddDataBaseFactory, boolean pAddSpringBean) {
        addDataBaseFactory = pAddDataBaseFactory;
        addSpringBean = pAddSpringBean;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext pApplicationContext) {
        applicationContext = pApplicationContext;
    }

    public boolean getAddDataBaseFactory() {
        return addDataBaseFactory;
    }

    public void setAddDataBaseFactory(boolean pAddDataBaseFactory) {
        addDataBaseFactory = pAddDataBaseFactory;
    }

    public boolean getAddSpringBean() {
        return addSpringBean;
    }

    public void setAddSpringBean(boolean pAddSpringBean) {
        addSpringBean = pAddSpringBean;
    }

    public List<DataSourceProperties> getConfigs() {
        return configs;
    }

    public void setConfigs(List<DataSourceProperties> pDataSource) {
        this.configs = pDataSource;
        applicationContext.publishEvent(new DataSourceListPropertiesLoadedEvent(this));
    }

    public void addSpring() {
        if (!addSpringBean) {
            return;
        }
        for (DataSourceProperties vItem : configs) {
            if (addDataBaseFactory) {
                AbstractDataAccess vDataSourceDataAccess = (AbstractDataAccess) tgtools.db.DataBaseFactory.get(vItem.getName());
                DataSource vDataSource = vDataSourceDataAccess.getDataSource();
                ((AnnotationConfigServletWebServerApplicationContext) applicationContext).getBeanFactory().registerSingleton(vItem.getName(), vDataSource);
            } else {
                DataSource vDataSource = createDataSource(vItem);
                ((AnnotationConfigServletWebServerApplicationContext) applicationContext).getBeanFactory().registerSingleton(vItem.getName(), vDataSource);
            }
        }
    }

    public void addDataBase() {
        if (!addDataBaseFactory) {
            return;
        }
        for (DataSourceProperties vItem : configs) {
            DataSourceDataAccess vDataSourceDataAccess = createDataSourceDataAccess(vItem);
            if (null != vDataSourceDataAccess) {
                try {
                    tgtools.db.DataBaseFactory.add(vItem.getName(), vDataSourceDataAccess);
                } catch (APPErrorException e) {
                    tgtools.util.LogHelper.error("DataSourceListProperties", "添加数据源错误！名称：" + vItem.getName(), "addDataSource", e);
                }
            }
        }
    }

    protected HikariDataSource createDataSource(DataSourceProperties pDataSourceProperties) {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl(pDataSourceProperties.getUrl());
        hikariConfig.setDriverClassName(pDataSourceProperties.getDriverClassName());
        hikariConfig.setUsername(pDataSourceProperties.getUsername());
        hikariConfig.setPassword(pDataSourceProperties.getPassword());
        hikariConfig.setMaximumPoolSize(pDataSourceProperties.getMaxActive());
        hikariConfig.setMinimumIdle(pDataSourceProperties.getMinIdle());
        hikariConfig.setConnectionTestQuery(pDataSourceProperties.getValidationQuery());
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(hikariConfig);
    }

    protected DataSourceDataAccess createDataSourceDataAccess(DataSourceProperties pDataSourceProperties) {
        try {
            DataSourceDataAccess vSpringDataAccess = new DataSourceDataAccess();
            vSpringDataAccess.init(createDataSource(pDataSourceProperties));
            return vSpringDataAccess;
        } catch (APPErrorException e) {
            tgtools.util.LogHelper.error("DataSourceListProperties", "添加数据源错误！名称：" + pDataSourceProperties.getName(), "addDataSource", e);
        }
        return null;
    }


}
