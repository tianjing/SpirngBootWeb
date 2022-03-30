package com.github.tianjing.example.empty.webapp.demo.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 田径
 * @date 2022-02-09 15:19
 * @desc
 **/
@RequestMapping("/thread")
@Controller
public class LockController {

    private int index=0;
    @Autowired
    LockService lockService;

    @RequestMapping("/test")
    public void test()
    {
        index = index +1 ;
        int i = index;
        System.out.println(i +"开始");
        lockService.process();
        System.out.println(i+"结束");

    }
}
