package com.github.tianjing.example.empty.webapp.demo.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 田径
 * @date 2022-02-09 15:21
 * @desc
 **/
@Service
public class LockService {
    public void process() {
        System.out.println("process");
        process1();
    }

    public synchronized void process1() {
        try {
            System.out.println("process1");
            Thread.sleep(10000);
        } catch (InterruptedException e) {

        }
    }
}
