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
@Document(indexName = "test-czl-czdw-ik-max-word")
public class BdzDO {
    public BdzDO(){}

    @Id
    private String id;

    @Field(type = FieldType.Keyword )
    private String czdw;

    /**
     *
     */
    @Field(type = FieldType.Text ,searchAnalyzer="ik_max_word",analyzer ="ik_max_word")
    private String cznr;
}

