package com.github.tianjing.tgtools.web.security.autoconfigure.properties;

import org.springframework.context.ApplicationEvent;

/**
 * @author 田径
 * @date 2020-03-06 16:15
 * @desc
 **/
public class DataSourceListPropertiesLoadedEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public DataSourceListPropertiesLoadedEvent(DataSourceListProperties source) {
        super(source);
    }


    public DataSourceListProperties getDataSourceListProperties() {
        return null != source ? (DataSourceListProperties) source : null;
    }

}