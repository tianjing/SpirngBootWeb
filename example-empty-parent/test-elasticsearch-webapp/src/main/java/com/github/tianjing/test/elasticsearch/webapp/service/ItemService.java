package com.github.tianjing.test.elasticsearch.webapp.service;

import com.github.tianjing.test.elasticsearch.webapp.dao.ItemRepository;
import com.github.tianjing.test.elasticsearch.webapp.model.ItemDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tgtools.tasks.Task;
import tgtools.tasks.TaskContext;
import tgtools.tasks.TaskRunner;
import tgtools.util.FileUtil;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author 田径
 * @date 2020-03-05 14:58
 * @desc
 **/
@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;
    private ArrayBlockingQueue<ItemDO> data = new ArrayBlockingQueue<ItemDO>(2000000);

    public void testWrite() {
        String vContent = FileUtil.readFile("C:\\Users\\tian_\\Desktop\\baike_qa_train.csv");
        String[] vRows = vContent.split("\t\n");
        System.out.println(vRows.length);
        for (int i = 0; i < vRows.length; i++) {
            try {
                String[] vColumn = vRows[i].split(",");
                //ItemDO vItem = new ItemDO(GUID.newGUID(), StringUtil.replace(vColumn[1], " ", ""), vColumn[0]);
                // data.add(vItem);
            } catch (Throwable e) {
            }
        }
        TaskRunner vRuner = new TaskRunner();
        for (int i = 0; i < 200; i++) {
            vRuner.add(new SaveTask(itemRepository, data));
        }
        vRuner.runThreadTillEndWithOutLock();
        System.out.println("=============================== testWrite end ===========================");
    }

    public static class SaveTask extends Task {
        ItemRepository itemRepository;
        ArrayBlockingQueue<ItemDO> data;

        public SaveTask(ItemRepository pItemRepository, ArrayBlockingQueue<ItemDO> pData) {
            itemRepository = pItemRepository;
            data = pData;
        }

        @Override
        protected boolean canCancel() {
            return false;
        }

        @Override
        public void run(TaskContext p_Param) {
            while (true) {
                ItemDO vItemDO = data.poll();
                if (null == vItemDO) {
                    return;
                }
                if (data.size() % 1000 == 0) {
                    System.out.println("data.size()" + data.size());
                }
                itemRepository.save(vItemDO);
            }
        }
    }
}
