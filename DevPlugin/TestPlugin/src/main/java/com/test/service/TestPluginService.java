package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 19:55
 */
@Service
public class TestPluginService {
    @Autowired
    DataSource dataSource;
    public void test()
    {
        System.out.println("pre test");
        System.out.println("dataSource:"+dataSource);
        System.out.println("after test");
    }
}
