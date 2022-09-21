package com.github.tianjing.test.alibaba.webapp.test;

import com.aliyun.oss.model.OSSObjectSummary;
import com.github.tianjing.tgtools.alibaba.oss.template.OssFileTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OssTestRunner implements ApplicationRunner {

    @Autowired
    private OssFileTemplate ossFileTemplate;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<OSSObjectSummary> vFiles = ossFileTemplate.list("t");
        System.out.println("========================================");
    }
}
