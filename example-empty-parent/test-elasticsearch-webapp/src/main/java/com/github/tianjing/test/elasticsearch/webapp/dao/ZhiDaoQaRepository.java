package com.github.tianjing.test.elasticsearch.webapp.dao;


import com.github.tianjing.test.elasticsearch.webapp.model.ZhiDaoQaDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author 田径
 * @date 2020-03-05 14:56
 * @desc
 **/
public interface ZhiDaoQaRepository extends ElasticsearchRepository<ZhiDaoQaDO, String> {
}
