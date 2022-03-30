package com.github.tianjing.example.empty.webapp.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.DeclarePrecedence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Aspect
@Configuration
public class AspectConfig {
    //defaultImpl 表示默认需要添加的新的类
    @DeclareParents(value = "com.github.tianjing.example.empty.webapp.aop.Women", defaultImpl = FemaleAnimal.class)
    public Animal animal;


    @Bean
    public AspectTestRunner aspectTest() {
        return new AspectTestRunner();
    }

    public static class AspectTestRunner implements ApplicationRunner {
        @Autowired
        ApplicationContext applicationContext;
        @Autowired
        Person person1;
//        @Autowired
//        Animal animal1;

        @Override
        public void run(ApplicationArguments args) throws Exception {
            Person person = (Person) applicationContext.getBean("women");
            person.likePerson();
            Animal animal = (Animal)person;
            animal.eat();
        }
    }
}
