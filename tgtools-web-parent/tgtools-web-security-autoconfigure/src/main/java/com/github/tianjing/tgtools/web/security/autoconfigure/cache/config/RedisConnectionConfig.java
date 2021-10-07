package com.github.tianjing.tgtools.web.security.autoconfigure.cache.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import tgtools.util.StringUtil;

import java.time.Duration;
import java.util.Arrays;

/**
 * @author 田径
 * @date 2019-07-13 22:52
 * @desc
 **/
@Configuration
public class RedisConnectionConfig {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory(
            @Qualifier("myRedisConfigEntity") RedisConfigEntity myRedisConfigEntity) {

        GenericObjectPoolConfig vPoolConfig = new GenericObjectPoolConfig();
        vPoolConfig.setMaxIdle(myRedisConfigEntity.getMaxIdle());
        vPoolConfig.setMaxTotal(myRedisConfigEntity.getMaxActive());
        vPoolConfig.setMinIdle(myRedisConfigEntity.getMinIdle());
        vPoolConfig.setMaxWaitMillis(myRedisConfigEntity.getMaxWait());

        LettuceClientConfiguration vLettuceClientConfiguration = LettucePoolingClientConfiguration.builder()
                .commandTimeout(Duration.ofSeconds(myRedisConfigEntity.getTimeout()))
                .poolConfig(vPoolConfig)
                .shutdownTimeout(Duration.ZERO)
                .build();

        LettuceConnectionFactory vLettuceConnectionFactory;
        if (StringUtil.isNullOrEmpty(myRedisConfigEntity.getNodes())) {
            RedisStandaloneConfiguration vRedisStandaloneConfiguration = new RedisStandaloneConfiguration();
            vRedisStandaloneConfiguration.setHostName(myRedisConfigEntity.getHost());
            vRedisStandaloneConfiguration.setPort(myRedisConfigEntity.getPort());
            vRedisStandaloneConfiguration.setDatabase(myRedisConfigEntity.getDatabase());
            vRedisStandaloneConfiguration.setPassword(RedisPassword.of(myRedisConfigEntity.getPassword()));

            vLettuceConnectionFactory = new LettuceConnectionFactory(vRedisStandaloneConfiguration, vLettuceClientConfiguration);
        } else {
            String[] nodestr = myRedisConfigEntity.getNodes().split(",");
            RedisClusterConfiguration vRedisClusterConfiguration = new RedisClusterConfiguration(Arrays.asList(nodestr));
            vRedisClusterConfiguration.setPassword(RedisPassword.of(myRedisConfigEntity.getPassword()));
            vRedisClusterConfiguration.setMaxRedirects(5);
            vLettuceConnectionFactory = new LettuceConnectionFactory(vRedisClusterConfiguration, vLettuceClientConfiguration);
            vLettuceConnectionFactory.setDatabase(myRedisConfigEntity.getDatabase());
        }

        return vLettuceConnectionFactory;
    }

    @ConfigurationProperties(prefix = "spring.redis.dev")
    @Bean("myRedisConfigEntity")
    public RedisConfigEntity myRedisConfigEntity() {
        return new RedisConfigEntity();
    }

    public static class RedisConfigEntity {
        private String host;
        private int port;
        private String password;
        private int database;

        private int timeout;
        private int maxActive;
        private int maxWait;
        private int maxIdle;
        private int minIdle;
        private String nodes;

        public String getHost() {
            return host;
        }

        public void setHost(String pHost) {
            host = pHost;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int pPort) {
            port = pPort;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String pPassword) {
            password = pPassword;
        }

        public int getDatabase() {
            return database;
        }

        public void setDatabase(int pDatabase) {
            database = pDatabase;
        }

        public int getTimeout() {
            return timeout;
        }

        public void setTimeout(int pTimeout) {
            timeout = pTimeout;
        }

        public int getMaxActive() {
            return maxActive;
        }

        public void setMaxActive(int paxActive) {
            maxActive = paxActive;
        }

        public int getMaxWait() {
            return maxWait;
        }

        public void setMaxWait(int paxWait) {
            maxWait = paxWait;
        }

        public int getMaxIdle() {
            return maxIdle;
        }

        public void setMaxIdle(int paxIdle) {
            maxIdle = paxIdle;
        }

        public int getMinIdle() {
            return minIdle;
        }

        public void setMinIdle(int pinIdle) {
            minIdle = pinIdle;
        }

        public String getNodes() {
            return nodes;
        }

        public void setNodes(String pNodes) {
            nodes = pNodes;
        }
    }

}
