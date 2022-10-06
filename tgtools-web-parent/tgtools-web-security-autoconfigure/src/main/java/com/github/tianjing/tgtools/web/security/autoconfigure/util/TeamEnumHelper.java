package com.github.tianjing.tgtools.web.security.autoconfigure.util;


import com.github.tianjing.tgtools.web.security.autoconfigure.util.enumext.EnumMapKey;

import java.lang.reflect.Field;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author 田径
 * @date 2020-02-27 14:57
 * @desc
 **/
public class TeamEnumHelper {
    protected static Map<String, Map<Enum, String>> cacheEnumMap = new ConcurrentHashMap<>();
    protected static Map<String, Map<Object, String>> cacheValueMap = new ConcurrentHashMap<>();
    protected static Map<String, Map<Object, Enum>> cacheValueEnumMap = new ConcurrentHashMap<>();


    /**
     * 将枚举每个枚举项和MapKey对应的描述组成一个Map，并返回；
     *
     * @param pEnum
     * @return
     */
    public static Map<Enum, String> getEnumMap(Class<? extends Enum> pEnum) {
        //检查缓存中是否存在
        if (cacheEnumMap.containsKey(pEnum.getName())) {
            return cacheEnumMap.get(pEnum.getName());
        }


        Map<Enum, String> vResult = new EnumMap<>((Class<Enum>) pEnum);
        Field[] fields = pEnum.getDeclaredFields();
        for (Field pField : fields) {
            try {
                EnumMapKey vName = pField.getAnnotation(EnumMapKey.class);
                if (null != vName) {
                    Enum vEnum = (Enum) pField.get(pEnum);
                    vResult.put(vEnum, vName.name());
                }
            } catch (Exception e) {

            }
        }


        cacheEnumMap.put(pEnum.getName(), vResult);
        return new EnumMap<>(vResult);
    }

    /**
     * 将枚举每个枚举值和MapKey对应的描述组成一个Map，并返回；
     *
     * @param pEnum
     * @return
     */
    public static Map<Object, String> getValueMap(Class<? extends Enum> pEnum) {
        //检查缓存中是否存在
        if (cacheValueMap.containsKey(pEnum.getName())) {
            return cacheValueMap.get(pEnum.getName());
        }


        Map<Object, String> vResult = new LinkedHashMap<>();
        Field[] fields = pEnum.getDeclaredFields();
        for (Field pField : fields) {
            try {
                EnumMapKey vName = pField.getAnnotation(EnumMapKey.class);
                if (null != vName) {

                    Enum vEnum = (Enum) pField.get(pEnum);
                    Field pValue = pEnum.getDeclaredField("value");
                    pValue.setAccessible(true);
                    Object pValuee = pValue.get(vEnum);
                    vResult.put(pValuee, vName.name());
                }
            } catch (Exception e) {

            }
        }
        cacheValueMap.put(pEnum.getName(), vResult);
        return new LinkedHashMap<>(vResult);
    }


    /**
     * 将枚举每个枚举值和MapKey对应的描述组成一个Map，并返回；
     *
     * @param pEnum
     * @return
     */
    public static Map<Object, Enum> getValueEnumMap(Class<? extends Enum> pEnum) {
        //检查缓存中是否存在
        if (cacheValueEnumMap.containsKey(pEnum.getName())) {
            return cacheValueEnumMap.get(pEnum.getName());
        }


        Map<Object, Enum> vResult = new LinkedHashMap<>();
        Field[] fields = pEnum.getDeclaredFields();
        for (Field pField : fields) {
            try {
                EnumMapKey vName = pField.getAnnotation(EnumMapKey.class);
                if (null != vName) {

                    Enum vEnum = (Enum) pField.get(pEnum);
                    Field pValue = pEnum.getDeclaredField("value");
                    pValue.setAccessible(true);
                    Object vValue = pValue.get(vEnum);
                    vResult.put(vValue, vEnum);
                }
            } catch (Exception e) {
            }
        }
        cacheValueEnumMap.put(pEnum.getName(), vResult);
        return new LinkedHashMap(vResult);
    }
}
