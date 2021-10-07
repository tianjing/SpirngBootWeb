package com.github.tianjing.example.empty.webapp.demo.gateway;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tgtools.web.develop.gateway.AbstractSingleGateway;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 10:21
 */
@Controller
@RequestMapping("/client")
public class SingleGateway extends AbstractSingleGateway {

    @Override
    protected String getCommandType() {
        return "rest";
    }
}