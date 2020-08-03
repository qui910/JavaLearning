package com.prd.anno;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component //声明组件
@Aspect //声明切面
public class SpringAspectJ {

    // 声明切点
    @Pointcut("execution(* com.prd.service..*.*(..) )")
    public void pointCut(){};

    // 声明通知  （位置 和 逻辑）位置就是指pointCut的位置
    @Before("pointCut()")
    public void before() {
        System.out.println("before");
    }
}
