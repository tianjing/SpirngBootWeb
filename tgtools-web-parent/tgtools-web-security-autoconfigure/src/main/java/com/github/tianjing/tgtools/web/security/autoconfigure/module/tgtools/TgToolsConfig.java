package com.github.tianjing.tgtools.web.security.autoconfigure.module.tgtools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ResourceUtils;
import tgtools.db.DataBaseFactory;
import tgtools.db.DataSourceDataAccess;
import tgtools.log.LoggerFactory;
import tgtools.message.MessageFactory;
import tgtools.plugin.PluginFactory;
import tgtools.util.LogHelper;
import tgtools.util.StringUtil;
import tgtools.web.platform.Platform;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.net.URL;

/**
 * @author 田径
 * @date 2021-10-04 21:50
 * @desc
 **/
public class TgToolsConfig {
    @Autowired
    ApplicationContext mApplicationContext;

    public TgToolsConfig() {
    }


    @PostConstruct
    public void init() {
        Platform.startup(this.mApplicationContext, false, false, false, false, false, false);
        fixServerPath();
        this.loadLogger();
        this.loadPlugins();
        this.loadMessage();
        LogHelper.info("", "fdasfds", "fdafdsa");
    }

    /**
     * 修复 将项目放入 lib 目录 启动后 项目路径修正为lib的上层目录
     * 例如： /home/a/lib 那么项目路径为 /home/a
     */
    protected void fixServerPath() {
        if (StringUtil.isNullOrEmpty(Platform.getServerPath())) {
            Field vField = tgtools.util.ReflectionUtil.findField(tgtools.web.platform.Platform.class, "m_BasePath");
            vField.setAccessible(true);
            String vPath = Thread.currentThread().getContextClassLoader().getResource("").toString();
            if (StringUtil.isNullOrEmpty(vPath)) {
                return;
            }

            if (vPath.endsWith("lib\\") || vPath.endsWith("lib/")) {
                vPath = vPath.substring(0, vPath.length() - 4);
            } else if (vPath.endsWith("lib")) {
                vPath = vPath.substring(0, vPath.length() - 3);
            }

            if (vPath.indexOf("file:/") >= 0) {
                vPath = "/" + vPath.substring(vPath.indexOf("file:/") + 6);
            }

            try {
                vField.set(null, vPath);
            } catch (IllegalAccessException e) {
                System.out.println("设置新项目路径出错！" + e.getMessage());
            }

        }

    }

    public void loadDataSource(DataSource pDataSource) {
        if (null == DataBaseFactory.getDefault()) {
            loadDataSource("MyDATAACCESS", pDataSource);
        }


    }

    public void loadDataSource(String pName, DataSource pDataSource) {
        try {

            DataSourceDataAccess vDataAccess = new DataSourceDataAccess();
            vDataAccess.init(pDataSource);
            DataBaseFactory.add(pName, new Object[]{vDataAccess});
            System.out.println("添加数据源：" + pName);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void loadLogger() {
        try {
            URL vUrl = null;
            vUrl = ResourceUtils.getURL("classpath:config/logback.xml");
            tgtools.util.LogHelper.setIsDegug(false);
            System.out.println("logurl:" + vUrl);

            //tgtools.util.LogHelper.setIsDegug(true);


            tgtools.web.log.logback.LogbackFactory.start(vUrl);
            LoggerFactory.getDefault().info("Logger 初始化完毕========");
        } catch (Exception ex) {
            LoggerFactory.getDefault().error("Logger 加载失败。", ex);
        }
    }

    protected void loadPlugins() {
        String vPath = Platform.getServerPath() + "Plugins/";

        try {
            PluginFactory.startup(vPath);
        } catch (Exception var3) {
            LoggerFactory.getDefault().error("插件管理器启动失败", var3);
        }

    }

    protected void loadMessage() {
        MessageFactory.start(false);
    }


}
