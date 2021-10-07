package com.github.tianjing.test.elasticsearch.webapp.dao;

import com.github.tianjing.test.elasticsearch.webapp.model.NoticeInfoDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author 田径
 * @date 2020-07-02 16:08
 * @desc
 **/
public interface NoticeInfoDao extends ElasticsearchRepository<NoticeInfoDO, String> {
}
