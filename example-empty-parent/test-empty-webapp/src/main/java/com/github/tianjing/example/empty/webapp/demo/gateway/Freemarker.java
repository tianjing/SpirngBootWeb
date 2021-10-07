package com.github.tianjing.example.empty.webapp.demo.gateway;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tgtools.data.DataRow;
import tgtools.data.DataTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 14:24
 */
@Controller
@RequestMapping("demo/freemarker")
public class Freemarker {

    @RequestMapping(value="index",method = {RequestMethod.GET})
    public ModelAndView index(ModelAndView pModel)
    {
        DataTable dt = new DataTable();
        dt.appendColumn("ID");
        dt.appendColumn("NAME");
        dt.appendColumn("SEX");
        DataRow row1 = dt.appendRow();
        row1.setValue("ID", "1");
        row1.setValue("NAME", "tg1");
        row1.setValue("SEX", "男");

        DataRow row2 = dt.appendRow();
        row2.setValue("ID", "2");
        row2.setValue("NAME", "tg2");
        row2.setValue("SEX", "女");

        List<A> list = new ArrayList<A>();
        A a = new A();
        a.setDt(dt);
        list.add(a);

        Map<String, B> dd = new HashMap<String, B>();
        dd.put("114", new B("222", "333"));
        dd.put("115", new B("444", "555"));

        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("message", "fda");
        data.put("table", dt);
        data.put("list1", list);
        data.put("marketMap", dd);


        pModel.setViewName("test");
        pModel.addAllObjects(data);
        return pModel;
    }

    public static class A {
        private DataTable dt;

        public DataTable getDt() {
            return dt;
        }

        public void setDt(DataTable dt) {
            this.dt = dt;
        }


    }

    public static class B {
        private String id;
        private String name;

        public B(String p_id, String p_name) {
            setId(p_id);
            setName(p_name);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


    }
}
