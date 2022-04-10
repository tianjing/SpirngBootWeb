package com.github.tianjing.example.erupt.webapp.model;

import org.hibernate.annotations.GenericGenerator;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.View;

import javax.persistence.*;
import java.util.Date;

/*
 *	@Erupt注解修饰在类上，@EruptField注解修饰在字段上
 *	其他注解均为Jpa注解
 */
@Erupt(name = "简单的例子")
@Table(name = "demo_simple")
@Entity
public class Simple {

    //主键
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "native")
    @Column(name = "id")
    @EruptField
    private Long id; //如果继承BaseModel不能再重复声明id

    //文本输入
    @EruptField(
            views = @View(title = "文本"),
            edit = @Edit(title = "文本")
    )
    private String input;

    //数值输入
    @EruptField(
            views = @View(title = "数值"),
            edit = @Edit(title = "数值")
    )
    private Integer number = 100;  //默认值100

    //布尔选择
    @EruptField(
            views = @View(title = "布尔"),
            edit = @Edit(title = "布尔")
    )
    private Boolean bool;

    //时间选择
    @EruptField(
            views = @View(title = "时间"),
            edit = @Edit(title = "时间")
    )
    private Date date;

}