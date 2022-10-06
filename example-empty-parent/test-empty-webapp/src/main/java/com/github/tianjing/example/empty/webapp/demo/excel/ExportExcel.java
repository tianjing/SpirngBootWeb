package com.github.tianjing.example.empty.webapp.demo.excel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import tgtools.excel.poi.ExportExcelImpl;
import tgtools.excel.poi.WorkbookFactory;
import tgtools.util.FileUtil;

import java.util.LinkedHashMap;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 13:32
 */
public class ExportExcel {
//    public static void main(String[] args)
//    {
//        try {
//            String filepath = "C:\\tianjing\\Desktop\\222.xls";
//            ExportExcelImpl export = new ExportExcelImpl();
//            export.init(WorkbookFactory.EXCEL_TYPE_XLS);
//            LinkedHashMap<String,String> columns=new LinkedHashMap<String,String>();
//            columns.put("ID","主键");
//            columns.put("NAME","名称");
//            columns.put("SEX","性别");
//            columns.put("BIR","生日");
//
//            ObjectMapper mapper =new ObjectMapper();
//            ArrayNode array= mapper.createArrayNode();
//            ObjectNode json= mapper.createObjectNode();
//            json.put("ID",1);
//            //columns 中没有的字段 不会被导出
//            json.put("ID1",1);
//            json.put("NAME","田径1");
//            json.put("SEX","男");
//            json.put("BIR","2013-12:12 12:00:00");
//            array.add(json);
//            ObjectNode json1= mapper.createObjectNode();
//            json1.put("ID",2);
//            json1.put("NAME","田径2");
//            json1.put("SEX","女");
//            json1.put("BIR","2014-12:12 12:00:00");
//            array.add(json1);
//            export.appendData(columns,array);
//            export.appendData(columns,array,true,"sheet2",1,2);
//            export.appendData(columns,array,true,"sheet3",2,0);
//
//            byte[] data= export.getBytes();
//            export.close();
//            FileUtil.writeFile(filepath,data);
//            System.out.println("结束");
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
}
