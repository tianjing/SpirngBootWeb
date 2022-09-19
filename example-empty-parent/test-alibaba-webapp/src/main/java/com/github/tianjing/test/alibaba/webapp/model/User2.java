package com.github.tianjing.test.alibaba.webapp.model;


import in.togetu.tablestore.repository.config.PrimaryId;
import in.togetu.tablestore.repository.config.TableStoreEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
@TableStoreEntity(name = "T_USER2")
public class User2 {

    @Id
    @PrimaryId(order = 1, name = "ID_", partition = true)
    private String id;

    @Column(name = "NAME")
    private String name;

}
