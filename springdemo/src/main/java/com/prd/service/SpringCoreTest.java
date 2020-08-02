package com.prd.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringCoreTest {
    public static void main(String[] args) {

        // xml编程风格，及set方法注入测试
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:spring.xml");
        SpringCoreDemoService service = (SpringCoreDemoService) context.getBean("service");
        service.Hello();
    }
}
