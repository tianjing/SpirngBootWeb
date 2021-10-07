package com.github.tianjing.example.empty.webapp.demo.gateway;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tgtools.web.develop.message.GridMessage;
import tgtools.web.develop.message.ResponseMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 8:49
 */
@Controller
@RequestMapping("demo/list")
public class ListGateway {
    @PostMapping(value = "data", produces = {"application/json"})
    @ResponseBody
    public GridMessage getListData() {
        GridMessage resutl = new GridMessage();
        try {
            List<MyData> dd = new ArrayList<MyData>();
            MyData data = new MyData();
            data.setA("fdasfdas");
            data.setB("321321");
            dd.add(data);

            MyData data1 = new MyData();
            data1.setA("2134532");
            data1.setB("fdasfdasfdsa");
            dd.add(data1);

            resutl.setData(dd);
            resutl.setTotal(200);
            resutl.setStatus(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resutl;
    }

    @PostMapping(value = "add", produces = {"application/json"})
    @ResponseBody
    public ResponseMessage add() {
        ResponseMessage result = new ResponseMessage();
        try {
            MyData data = new MyData();
            data.setA("fdasfdas");
            data.setB("312321");
            result.setData(data);
            result.setStatus(true);
        }catch (Exception ex)
        {
            result.setStatus(false);
            result.setData(ex.getMessage());
        }
        return result;
    }


    public static class MyData {
        private String a;
        private String b;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    }
}
