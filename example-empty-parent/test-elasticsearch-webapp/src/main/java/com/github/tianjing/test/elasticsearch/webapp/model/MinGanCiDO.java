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
@Document(indexName = "teamfour-elasticsearch-client-test2-minganci")
public class MinGanCiDO {
    public MinGanCiDO(){}
    @Id
    private String id;
    /**
     *
     */
    @Field(type = FieldType.Text ,searchAnalyzer="ik_smart",analyzer ="ik_smart")
    private String content;

    @Field(type = FieldType.Keyword )
    private String ci;
    /**
     * 分类
     */
    @Field(type = FieldType.Keyword)
    private String type;
}

