package com.github.tianjing.example.empty.webapp.demo.task;

import tgtools.tasks.Task;
import tgtools.tasks.TaskContext;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 10:53
 */
public class MyTask extends Task {
    @Override
    protected boolean canCancel() {
        return false;
    }

    @Override
    public void run(TaskContext p_Param) {
        System.out.println("fdafdsa");
    }
    //非线程 调用
    //线程调用
    //线程调用 中途退出
}
