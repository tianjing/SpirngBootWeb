package com.github.tianjing.test.drools.webapp.controller;

import com.github.tianjing.test.drools.webapp.model.Address;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 田径
 * @date 2020-05-02 20:10
 * @desc
 **/
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private KieSession kieSession;

    @ResponseBody
    @RequestMapping(value = "/address/{postCode}", method = RequestMethod.GET)
    public void test(@PathVariable(value = "postCode") String postCode) {
        // 以下的数据可以从数据库来，这里写死了
        Address address = new Address();
        address.setPostcode(postCode);
        // 使用规则引擎
        kieSession.insert(address);
        int ruleFiredCount = kieSession.fireAllRules();
        System.out.println("触发了" + ruleFiredCount + "条规则");
        System.out.println("---------------------------------");
    }

}
