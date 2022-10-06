package com.github.tianjing.test.alibaba.webapp.model;

import in.togetu.tablestore.repository.config.PrimaryId;
import in.togetu.tablestore.repository.config.TableStoreEntity;
import lombok.Data;

import javax.persistence.Column;

/**
 * 浏览记录
 *
 * @author 王磊
 * @date 2022-01-11
 */
@TableStoreEntity(name = "x_history")
@Data
public class XHistory {

    @PrimaryId(order = 1, name = "userId", partition = true)
    Long userId;

    @Column( name = "createTime")
    Long createTime;

    @Column( name = "objectId")
    String objectId;


    @Column( name = "objectType")
    ObjectType objectType;


    @Column( name = "title")
    String title;

    @Column( name = "cover")
    String cover;


    public enum ObjectType {
        COMMENT,//评论
        COMMODITY,//商品
        COURSE_SUBJECT,//科目
        COURSE_ITEM,//课时
        CONTENT,//内容
        USER,//用户
        DYNAMIC,//动态
    }
}
