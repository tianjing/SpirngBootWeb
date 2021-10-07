package com.github.tianjing.example.empty.webapp.demo.excel;

import com.fasterxml.jackson.databind.node.ArrayNode;
import tgtools.excel.poi.ImportExcelImpl;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 13:32
 */
public class ImportExcel {
    public static void main1(String[] args) {
        String filepath = "C:\\tianjing\\Desktop\\221.xls";
        ImportExcelImpl importExcel = new ImportExcelImpl();
        LinkedHashMap<String, String> columns = new LinkedHashMap<String, String>();
        columns.put("ID", "主键");
        columns.put("NAME", "名称");
        //columns.put("SEX","性别");
        columns.put("BIR", "生日");
        HashMap<String, String> table = new HashMap<String, String>();
        table.put("sheet1", "MQ_SYS.ACT_ID_USER");
        //默认不做数据库操作 之转换成json
        importExcel.init(columns, table);

        //设置数据库类型后进行sql 操作
        //importExcel.init(columns, table,"dm");
        try {
            importExcel.importExcel(new File(filepath));
            Map<String, ArrayNode> ds = importExcel.getParseData();
            importExcel.close();
            System.out.println(ds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filepath = "C:\\tianjing\\Desktop\\220kV_监控设备台账模板.xls";
        ImportExcelImpl importExcel = new ImportExcelImpl();
        LinkedHashMap<String, String> columns = new LinkedHashMap<String, String>();
        columns.put("ID", "序号");
        columns.put("NAME", "间隔");
        columns.put("BIR", "设备类别");
        HashMap<String, String> table = new HashMap<String, String>();
        table.put("sheet1", "MQ_SYS.ACT_ID_USER");
        //默认不做数据库操作 之转换成json
        importExcel.init(columns, table);

        //设置数据库类型后进行sql 操作
        //importExcel.init(columns, table,"dm");
        try {
            importExcel.importExcel(new File(filepath));
            Map<String, ArrayNode> ds = importExcel.getParseData();
            importExcel.close();
            System.out.println(ds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
