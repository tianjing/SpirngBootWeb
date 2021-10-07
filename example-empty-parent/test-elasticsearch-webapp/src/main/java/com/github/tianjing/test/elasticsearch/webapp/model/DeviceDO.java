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
@Document(indexName = DeviceDO.INDEX_NAME)
public class DeviceDO {
    public static final String INDEX_NAME ="test-ycjx-year-device-ik-max-word";
    public DeviceDO(){}

    @Id
    private String id;


    /**
     *
     */
    @Field(type = FieldType.Text ,searchAnalyzer="ik_max_word",analyzer ="ik_max_word")
    private String deviceName;
}

