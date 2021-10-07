package com.github.tianjing.example.empty.webapp.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import tgtools.notify.rabbitmq.config.RabbitConfig;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 16:22
 */
//请确保rabbitmq配置正确再启用
@Order(0)
//@Configuration
public class RabbitMqConfig extends RabbitConfig {
    @Bean
    @Override
    public ConnectionFactory getConnectionFactory() {
        CachingConnectionFactory conn = new CachingConnectionFactory();
        conn.setHost("192.168.88.134");
        //conn.setPort();
        conn.setVirtualHost("/test");
        conn.setUsername("test");
        conn.setPassword("test123");
        return conn;
    }

}
