package com.github.tianjing.example.empty.webapp.demo.mybatis.db1;

import tgtools.web.develop.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 8:54
 */
@Table(name = "ACT_ID_USER")
public class MyUserDO extends BaseModel {
    @Column(name="FIRST_")
    private String mUserName;
    @Column(name="PWD_")
    private String mPassword;


    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String pUserName) {
        mUserName = pUserName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String pPassword) {
        mPassword = pPassword;
    }
}
