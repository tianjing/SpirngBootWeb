package com.github.tianjing.example.empty.webapp.controller;


import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/test2")
public class Test2Controller {

    @RequestMapping("/test2")
    public void test2() throws Exception {
        throw new Exception("test2");
    }

    /**
     * 添加测试
     * @param file
     * @param imageInfo
     * @return
     */
    @PostMapping("upload")
    public ObjectNode upload(@RequestPart("file") MultipartFile file, @RequestPart("imageInfo") ObjectNode imageInfo) {
        System.out.println(imageInfo);
        return imageInfo;
    }
}
