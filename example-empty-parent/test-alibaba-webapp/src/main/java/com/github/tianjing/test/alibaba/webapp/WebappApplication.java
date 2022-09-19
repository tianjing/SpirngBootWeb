package com.github.tianjing.test.alibaba.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import tgtools.util.LogHelper;

/**
 * @author 田径
 * @create 2019-02-13 10:05
 * @desc
 **/

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class WebappApplication {

    public static void main(String[] args) {
        System.setProperty("http.proxySet", "true");
        System.setProperty("proxyHost", "127.0.0.1"); // PROXY_HOST：代理的IP地址
        System.setProperty("proxyPort", "8888"); // PROXY_PORT：代理的端口号
        SpringApplication.run(WebappApplication.class, args);
        LogHelper.info("", "==================================================", "main");
    }

}
