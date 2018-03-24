package tgtools.spirngbootweb.demo.gateway;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tgtools.web.entity.GridData;
import tgtools.web.entity.ResposeData;


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
public class ListController {
    @PostMapping(value = "data", produces = {"application/json"})
    @ResponseBody
    public GridData getListData() {
        GridData resutl = new GridData();
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
            resutl.setPageSize(10);
            resutl.setCurPage(1);
            resutl.setTotalRows(200);
            resutl.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resutl;
    }

    @PostMapping(value = "add", produces = {"application/json"})
    @ResponseBody
    public ResposeData add() {
        ResposeData result = new ResposeData();
        try {
            MyData data = new MyData();
            data.setA("fdasfdas");
            data.setB("312321");
            result.setData(data);
            result.setSuccess(true);
        }catch (Exception ex)
        {
            result.setSuccess(false);
            result.setError(ex.getMessage());
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
