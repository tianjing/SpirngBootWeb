package com.test;

import com.test.util.LogHelper;
import org.quartz.JobExecutionContext;
import tgtools.service.BaseService;
import tgtools.tasks.TaskContext;
import tgtools.util.DateUtil;

import java.util.Date;


public class TestServices extends BaseService {
    /**
     * 无效 由cron管理
     */
    @Override
    protected int getInterval() {
        // TODO Auto-generated method stub
        return 10000;
    }

    /**
     * 无效 由cron管理
     */
    @Override
    protected Date getEndTime() {
        // TODO Auto-generated method stub
        return DateUtil.getMaxDate();
    }

    /**
     * 是否可运行 可以增加自定义业务判断 必须
     *
     * @return
     */
    @Override
    public boolean canRun() {
        return true;
    }

    /**
     * 服务的主要执行代码 必须
     */
    @Override
    public void run(TaskContext p_Param) {
        tgtools.web.services.ServicesEntity entity = (tgtools.web.services.ServicesEntity)p_Param.get("info");
        JobExecutionContext jobContext=(JobExecutionContext)p_Param.get("jobContext");

        try {
            LogHelper.info("开始服务开始");
            Thread.sleep(3000);
            LogHelper.info("服务结束");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}
