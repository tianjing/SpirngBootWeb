package com.github.tianjing.example.empty.webapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/test1")
@Controller
public class TestController {

    @RequestMapping("/cookieValuetest")
    public void cookieValueTest(@CookieValue(value = "JSESSIONID") String sessionId) {
        System.out.println("JSESSIONID:" + sessionId);
    }


    @RequestMapping("/test1")
    public void test1() throws Exception {
        throw new Exception("test1");
    }

    @ExceptionHandler(Exception.class)//可以直接写@EceptionHandler，IOExeption继承于Exception
    @ResponseBody
    public String allExceptionHandler(Exception exception) {
        return exception.toString();
    }
}
