package com.github.tianjing.example.empty.webapp.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 13:59
 */
@Aspect
@Component
public class LogAspect {
    @Pointcut("execution(public * tgtools.spirngbootweb.demo.mybatis.db1.MyUserDao.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        System.out.println("webLog12 deBefore");
    }
}
