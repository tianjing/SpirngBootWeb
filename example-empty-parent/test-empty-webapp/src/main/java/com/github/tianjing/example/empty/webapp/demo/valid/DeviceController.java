package com.github.tianjing.example.empty.webapp.demo.valid;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.Max;

/**
 * @author 田径
 * @date 2021-11-18 16:55
 * @desc
 **/
@RequestMapping
@Controller
@Validated
public class DeviceController {


    @ResponseBody
    @RequestMapping("/test1")
    public String test1(@Max(message = "年龄不能大于150岁", value = 150) Integer age,
                        @Length(max = 10, min = 2, message = "名字应该在2-10个文字之间") String name) {
        return age + ":" + name;
    }

    @ResponseBody
    @RequestMapping("/test2")
    public String test2(@Valid @RequestBody DeviceDO pDeviceDO) {
        return pDeviceDO.toString();
    }
}
