package com.github.tianjing.example.empty.webapp.demo.gateway;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tgtools.plugin.IPlugin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 15:06
 */
@Controller
@RequestMapping("demo/plugins")
public class PluginsGateway {

    @RequestMapping(value = "/pingyin", method = RequestMethod.GET)
    public void pingyin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        StringBuilder sb = new StringBuilder();
        IPlugin plugin = tgtools.plugin.PluginFactory.getPlugin("pinyinplugin");
        String res1 = "";
        String res2 = "";
        String res3 = "";
        if (null != plugin) {
            res1 = (String) plugin.execute("田径");
            res2 = (String) plugin.execute("田径", ";");
            res3 = (String) plugin.execute("田径", ";", true);

            sb.append("res1:" + res1);
            sb.append("\\n");
            sb.append("res2:" + res2);
            sb.append("\\n");
            sb.append("res3:" + res3);
            sb.append("\\n");

        }
        OutputStream os = response.getOutputStream();
        os.write(sb.toString().getBytes("UTF-8"));
        os.flush();
        os.close();
    }

    @RequestMapping(value = "/mail", method = RequestMethod.GET)
    public void mail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String smtp = "smtp.163.com";
        String frommail = "tianjing@fda.com";
        String frompassword = "password";
        String fromname = "tianjinggggg";
        String tomail = "tianjing111@fda.com";
        String toname = "tianji";
        String toTitle = "测是1";
        String toContent = "<html><head></head><body>你好</body></html>";

        IPlugin mail = tgtools.plugin.PluginFactory.getPlugin("mailplugin");
        mail.execute(smtp, frommail, frompassword, fromname, tomail, toname, toTitle, toContent);

        OutputStream os= response.getOutputStream();
        os.write("success".getBytes("UTF-8"));
        os.flush();
        os.close();
    }
}
