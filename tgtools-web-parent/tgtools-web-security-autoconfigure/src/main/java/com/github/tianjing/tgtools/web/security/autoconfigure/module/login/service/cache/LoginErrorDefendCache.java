package com.github.tianjing.tgtools.web.security.autoconfigure.module.login.service.cache;

import tgtools.exceptions.APPErrorException;

/**
 * @author 田径
 * @date 2020-08-26 11:17
 * @desc
 **/
public interface LoginErrorDefendCache {
    String CACHE_KEY_USER_LOGIN_ERROR = "CACHE_KEY_USER_LOGIN_ERROR";
    /**
     * 添加缓存
     * @param pUserName
     * @param pSeconds
     * @throws APPErrorException
     */
    void add(String pUserName,int pSeconds) throws APPErrorException;

    /**
     * 获取缓存
     * @param pUserName
     * @throws APPErrorException
     */
    Integer get(String pUserName) throws APPErrorException;



}
