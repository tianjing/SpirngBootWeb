package com.github.tianjing.test.elasticsearch.webapp.service;

import com.github.tianjing.test.elasticsearch.webapp.dao.ZhiDaoQaRepository;
import com.github.tianjing.test.elasticsearch.webapp.model.ZhiDaoQaDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tgtools.exceptions.APPErrorException;
import tgtools.tasks.Task;
import tgtools.tasks.TaskContext;
import tgtools.tasks.TaskRunner;
import tgtools.util.StringUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author 田径
 * @date 2020-03-05 14:58
 * @desc
 **/
@Service
public class ZhiDaoQaService {

    @Autowired
    private ZhiDaoQaRepository zhiDaoQaRepository;
    private ArrayBlockingQueue<ZhiDaoQaDO> data = new ArrayBlockingQueue<ZhiDaoQaDO>(99999999);

    public static void main(String[] args) throws APPErrorException, IOException {
        new ZhiDaoQaService().testWrite();
    }

    public void testWrite() throws APPErrorException, IOException {
        File vFile = new File("C:\\Users\\tian_\\Desktop\\zhidao_qa.json");

        BufferedReader vReader = new BufferedReader(new FileReader(vFile));
        String vRowContent = null;
        while (StringUtil.isNotEmpty(vRowContent = vReader.readLine())) {
            ZhiDaoQaDO vRow = tgtools.util.JsonParseHelper.parseToObject(vRowContent, ZhiDaoQaDO.class, false);
            //vRow.setId(GUID.newGUID());
            data.add(vRow);

        }

        TaskRunner vRuner = new TaskRunner();
        for (int i = 0; i < 200; i++) {
            vRuner.add(new SaveTask(zhiDaoQaRepository, data));
        }
        vRuner.runThreadTillEndWithOutLock();
        System.out.println("=============================== testWrite end ===========================");
    }

    public static class SaveTask extends Task {
        ZhiDaoQaRepository zhiDaoQaRepository;
        ArrayBlockingQueue<ZhiDaoQaDO> data;

        public SaveTask(ZhiDaoQaRepository pZhiDaoQaRepository, ArrayBlockingQueue<ZhiDaoQaDO> pData) {
            zhiDaoQaRepository = pZhiDaoQaRepository;
            data = pData;
        }

        @Override
        protected boolean canCancel() {
            return false;
        }

        @Override
        public void run(TaskContext p_Param) {
            while (true) {
                ZhiDaoQaDO vItemDO = data.poll();
                if (null == vItemDO) {
                    return;
                }
                if (data.size() % 1000 == 0) {
                    System.out.println("data.size()" + data.size());
                }
                zhiDaoQaRepository.save(vItemDO);
            }
        }
    }
}
