package com.github.tianjing.tgtools.web.security.autoconfigure.properties;

import org.springframework.context.ApplicationListener;

/**
 * @author 田径
 * @date 2020-03-06 16:18
 * @desc
 **/
public class DataSourceListPropertiesLoadedListener implements ApplicationListener<DataSourceListPropertiesLoadedEvent> {
    protected boolean isLoad = false;

    @Override
    public void onApplicationEvent(DataSourceListPropertiesLoadedEvent event) {
        if (!isLoad) {
            event.getDataSourceListProperties().addDataBase();
            event.getDataSourceListProperties().addSpring();
            isLoad = true;
        }
    }
}
