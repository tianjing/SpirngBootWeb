package com.github.tianjing.test.elasticsearch.webapp.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//
//import java.util.Date;
//
///**
// * @author 田径
// * @date 2020-03-26 15:08
// * @desc
// **/
//@Data
//@AllArgsConstructor
//@Document(indexName = "teamfour-elasticsearch-client-test2-file")
//public class FileDO {
//
//
//    @Id
//    private String id;
//    /**
//     * 标题
//     */
//    @Field(type = FieldType.Text, analyzer = "ik_max_word")
//    private String name;
//    @Field(type = FieldType.Text, analyzer = "ik_max_word")
//    private String content;
//    @Field(type = FieldType.Keyword)
//    private String host;
//    @Field(type = FieldType.Keyword)
//    private String path;
//    @Field(type = FieldType.Keyword)
//    private String protocol;
//    @Field(type = FieldType.Integer)
//    private Integer port;
//    @Field(type = FieldType.Date)
//    private Date editTime;
//
//    public FileDO() {
//    }
//
//}
