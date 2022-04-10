package com.github.tianjing.example.erupt.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import xyz.erupt.core.annotation.EruptScan;

/**
 * @author 田径
 * @create 2019-02-13 10:05
 * @desc
 **/
@EntityScan
@EruptScan
@SpringBootApplication
public class WebappApplication {


    public static void main(String[] args) {
        SpringApplication.run(WebappApplication.class, args);
    }

}
