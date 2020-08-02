package com.prd.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringCoreTest {
    public static void main(String[] args) {

        // xml和注解 混合编程风格，及set方法注入测试
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:spring.xml");
        SpringCoreDemoService service = (SpringCoreDemoService) context.getBean("service");
        service.Hello();


        // 单纯JavaConfig方式
//        AnnotationConfigApplicationContext context = new
//                AnnotationConfigApplicationContext(SpringJavaConfig.class);
//        SpringCoreDemoService service = (SpringCoreDemoService) context.getBean("service");
//        service.Hello();


        // 自动装配
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
//                "classpath:spring1.xml");
//        SpringCoreDemoService service = (SpringCoreDemoService) context.getBean("service");
//        service.Hello();
//        service.Hello1();

        // 自动装配 注解
//        AnnotationConfigApplicationContext context = new
//                AnnotationConfigApplicationContext(SpringJavaConfig.class);
//        SpringCoreDemoService service = (SpringCoreDemoService) context.getBean("service");
//        System.out.println(service.hashCode());
//        service.Hello();
//        service.Hello1();

    }
}
