package com.github.tianjing.tgtools.web.security.autoconfigure.util;

import tgtools.data.DataRow;
import tgtools.data.DataTable;
import tgtools.exceptions.APPErrorException;
import tgtools.json.JSONArray;
import tgtools.json.JSONObject;

import java.util.Iterator;

/**
 * DataTable 帮助类
 * @author
 */
public class TeamDataTableHelper {

    /**
     * 单行复制
     * 将 json 的内容 增加到 datatable 中，前提 字段名称一致的 情况下
     *
     * @param pTable
     * @param pJson
     */
    public static void addRow(DataTable pTable, JSONObject pJson) throws APPErrorException {
        JSONObject vNewJson = filterByColumn(pTable, pJson);

        if (vNewJson.length() < 1) {
            return;
        }

        DataRow vRow = pTable.appendRow();
        Iterator vIterator = vNewJson.keys();
        while (vIterator.hasNext()) {
            String vName = (String) vIterator.next();
            vRow.setValue(vName, pJson.get(vName));
        }
    }

    /**
     * 多行复制
     * 将 json 的内容 增加到 datatable 中，前提 字段名称一致的 情况下
     *
     * @param pTable
     * @param pJson
     */
    public static void addRows(DataTable pTable, JSONArray pJson) throws APPErrorException {
        for (int i = 0; i < pJson.length(); i++) {
            addRow(pTable, pJson.getJSONObject(i));
        }
    }

    /**
     * 根据 datatable 过滤 json ，只保留 与 datatable 列名一致的属性
     *
     * @param pTable
     * @param pJson
     * @return
     */
    public static JSONObject filterByColumn(DataTable pTable, JSONObject pJson) throws APPErrorException {
        JSONObject vJson = new JSONObject();
        Iterator vIterator = pJson.keys();
        while (vIterator.hasNext()) {
            String vName = (String) vIterator.next();
            if (pTable.hasColumn(vName)) {
                vJson.put(vName, pJson.get(vName));
            }
        }
        return vJson;
    }

}