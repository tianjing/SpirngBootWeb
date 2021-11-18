package com.github.tianjing.test.db.webapp.model;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author 田径
 * @date 2021-11-18 16:26
 * @desc
 **/
@Validated
public class UserDO {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String pName) {
        name = pName;
    }
}
