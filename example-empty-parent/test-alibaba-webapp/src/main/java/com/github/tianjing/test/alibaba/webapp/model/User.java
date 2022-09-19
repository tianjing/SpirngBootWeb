package com.github.tianjing.test.alibaba.webapp.model;


import javax.persistence.Column;
import javax.persistence.Id;
import in.togetu.tablestore.repository.config.*;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@TableStoreEntity(name = "T_USER")
public class User {

    @Id
    @PrimaryId(order = 1, name = "ID_", partition = true)
    private String id;

    @Column(name="COL1")
    private String col1;
}
