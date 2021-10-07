package com.github.tianjing.test.elasticsearch.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author 田径
 * @date 2020-03-05 14:53
 * @desc
 **/
@Data
@AllArgsConstructor
@Document(indexName = "teamfour-elasticsearch-client-test2-item")
public class ItemDO {
    public ItemDO(){}
    @Id
    private String id;
    /**
     *  标题
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word" ,searchAnalyzer="ik_max_word")
    private String content;
    /**
     * 分类
     */
    @Field(type = FieldType.Keyword)
    private String type;
}

