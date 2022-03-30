package com.github.tianjing.example.empty.webapp.aop;


import org.springframework.stereotype.Component;

@Component
public class FemaleAnimal implements Animal {

    @Override
    public void eat() {
        System.out.println("我是雌性，我比雄性更喜欢吃零食");
    }
}
