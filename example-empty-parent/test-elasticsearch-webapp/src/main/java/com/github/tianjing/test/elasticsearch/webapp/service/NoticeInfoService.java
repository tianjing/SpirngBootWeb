package com.github.tianjing.test.elasticsearch.webapp.service;

import com.github.tianjing.test.elasticsearch.webapp.dao.NoticeInfoDao;
import com.github.tianjing.test.elasticsearch.webapp.model.NoticeFileDO;
import com.github.tianjing.test.elasticsearch.webapp.model.NoticeInfoDO;
import com.github.tianjing.test.elasticsearch.webapp.model.NoticeUserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 田径
 * @date 2020-07-02 16:08
 * @desc
 **/
@Service
public class NoticeInfoService {

    @Autowired
    NoticeInfoDao noticeInfoDao;

    public void testAdd() {
        for (int i = 0; i < 10000; i++) {
             NoticeInfoDO vNoticeInfoDO = new NoticeInfoDO(String.valueOf(i),(long)i,
                      String.valueOf(i), String.valueOf(i));
             noticeInfoDao.save(vNoticeInfoDO);
            noticeInfoDao.save(vNoticeInfoDO);
            System.out.println("");
        }

    }

    public List<NoticeFileDO> buildFiles() {
        List<NoticeFileDO> vFiles = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            // vFiles.add(new NoticeFileDO(String.valueOf(i), String.valueOf(i)));
        }
        return vFiles;
    }


    public List<NoticeUserDO> buildUsers() {
        List<NoticeUserDO> vUsers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            // vUsers.add(new NoticeUserDO(String.valueOf(i), String.valueOf(i)));
        }
        return vUsers;
    }

}
