package com.github.tianjing.test.elasticsearch.webapp.dao;



import com.github.tianjing.test.elasticsearch.webapp.model.BdzDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author 田径
 * @date 2020-03-05 14:56
 * @desc
 **/
public interface BdzRepository extends ElasticsearchRepository<BdzDO, String> {
}
