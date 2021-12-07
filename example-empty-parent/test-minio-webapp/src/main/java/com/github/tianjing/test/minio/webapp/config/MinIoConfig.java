package com.github.tianjing.test.minio.webapp.config;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 田径
 * @date 2021-12-07 14:15
 * @desc
 **/
@Configuration
public class MinIoConfig {

    @Value("${minio.url:http://192.168.1.245:5001}")
    private String url;

    @Value("${minio.user:minio}")
    private String user;
    @Value("${minio.password:minio123456}")
    private String password;


    @Bean
    public MinioClient minioClient() throws InvalidPortException, InvalidEndpointException {
        MinioClient client = new MinioClient(url, user, password);
        return client;
    }

}
