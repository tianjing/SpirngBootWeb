package com.github.tianjing.tgtools.web.security.autoconfigure.util;

import tgtools.exceptions.APPErrorException;

import java.lang.reflect.Constructor;

/**
 * @author 田径
 * @date 2019-12-31 10:59
 * @desc
 **/
public class TeamReflectionHelper {

    /**
     * 创建一个
     * @param pNewClass
     * @param paramsClass
     * @param params
     * @return
     * @throws APPErrorException
     */
    public static Object createObject(String pNewClass, Class[] paramsClass, Object[] params) throws APPErrorException {
        Class vClass = null;

        try {
            vClass = Class.forName(pNewClass);
        } catch (ClassNotFoundException e) {
            throw new APPErrorException("查找Classc 出错！" + pNewClass, e);
        }

        Constructor vConstructor = null;
        try {
            vConstructor = vClass.getConstructor(paramsClass);
        } catch (NoSuchMethodException e) {
            throw new APPErrorException("获取构造方法出错！" + pNewClass, e);
        }


        Object vObject = null;
        try {
            vObject = vConstructor.newInstance(params);
        } catch (Exception e) {
            throw new APPErrorException("newInstance 出错！" + pNewClass, e);
        }

        return vObject;
    }
}
