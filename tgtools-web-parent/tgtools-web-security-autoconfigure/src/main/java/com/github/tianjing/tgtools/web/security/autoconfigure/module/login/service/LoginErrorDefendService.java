package com.github.tianjing.tgtools.web.security.autoconfigure.module.login.service;


import tgtools.exceptions.APPErrorException;

/**
 * 登录失败的保护同能
 * @author
 */
public interface LoginErrorDefendService {

    /**
     * 添加用户登录错误信息
     * 每登录出错记录1次
     * @param pLoginName
     * @throws APPErrorException
     */
    void addUserErrorLogin(String pLoginName) throws APPErrorException;

    /**
     * 验证用户是否登录错误达到上限
     * @param pLoginName
     * @throws APPErrorException
     */
    void validUserErrorLogin(String pLoginName) throws APPErrorException;
}
