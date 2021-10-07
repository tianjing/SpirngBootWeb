package com.github.tianjing.test.elasticsearch.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 14:16
 */
@Data
@AllArgsConstructor
@Document(indexName = "wcpt_act_notice")
public class NoticeInfoDO {
    public NoticeInfoDO(){}
    @Id
    private String id;
    @Field(type = FieldType.Long)
    private Long rev;

    @Field(type = FieldType.Keyword)
    private String title;

    @Field(type = FieldType.Text, searchAnalyzer = "ik_smart",analyzer ="ik_smart")
    private String content;

//    @Field(type = FieldType.Keyword)
//    private String createUser;
//    @Field(type = FieldType.Date,format= DateFormat.basic_date)
//    private Date createTime;
//
//    @Field(type = FieldType.Integer)
//    private Integer deploy;
//
//    @Field(type = FieldType.Integer)
//    private Integer template;
//
//    @Field(type = FieldType.Date,format= DateFormat.basic_date)
//    private Date endTime;
//
//    @Field(type = FieldType.Text, analyzer = "ik_smart")
//    private String appendContent;
//
//    @Field(type = FieldType.Object)
//    private List<NoticeFileDO> files;
//
//    @Field(type = FieldType.Object)
//    private List<NoticeUserDO> users;

}
