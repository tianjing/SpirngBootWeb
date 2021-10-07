package com.github.tianjing.test.elasticsearch.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
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
public class NoticeFileDO {
    @Field(type = FieldType.Keyword)
    private String noticeId;
    @Field(type = FieldType.Keyword)
    private String fileId;
}
