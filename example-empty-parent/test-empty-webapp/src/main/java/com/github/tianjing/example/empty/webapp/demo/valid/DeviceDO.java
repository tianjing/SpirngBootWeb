package com.github.tianjing.example.empty.webapp.demo.valid;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;

/**
 * @author 田径
 * @date 2021-11-18 16:55
 * @desc
 **/
public class DeviceDO {

    @Max(message = "年龄不能大于150岁", value = 150)
    private Integer age;
    @Length(max = 10, min = 2, message = "名字应该在2-10个文字之间")
    private String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer pAge) {
        age = pAge;
    }

    public String getName() {
        return name;
    }

    public void setName(String pName) {
        name = pName;
    }
}
