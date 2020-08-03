package com.prd.service;

import com.prd.anno.Entry;
import com.prd.model.CityEntry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringCoreTest {
    public static void main(String[] args) {

        // xml和注解 混合编程风格，及set方法注入测试
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
//                "classpath:spring.xml");
//        SpringCoreDemoService service = (SpringCoreDemoService) context.getBean("service");
//        service.Hello();


        // 单纯JavaConfig方式
        AnnotationConfigApplicationContext context = new
                AnnotationConfigApplicationContext(SpringJavaConfig.class);
        SpringCoreDemoService service = (SpringCoreDemoService) context.getBean("service");
        service.Hello();


        // 自动装配
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
//                "classpath:spring1.xml");
//        SpringCoreDemoService service = (SpringCoreDemoService) context.getBean("service");
//        service.Hello();
//        service.Hello1();

        // 自动装配 注解
//        AnnotationConfigApplicationContext context = new
//                AnnotationConfigApplicationContext(SpringJavaConfig.class);
//        context.getEnvironment().setActiveProfiles("mysql");//多个环境切换
//        context.register(SpringJavaConfig.class);
//        context.refresh();
//        SpringCoreDemoService service = (SpringCoreDemoService) context.getBean("service");
//        System.out.println(service.hashCode());
//        service.Hello();
//        service.Hello1();

//        Class clazz = CityEntry.class;
//        // 判断类上是否有Entry注解，注意这里必须Entry有RetentionPolicy.RUNTIME，如果是其他值这里判断为false
//        System.out.println(clazz.isAnnotationPresent(Entry.class));
//        // 得到注解
//        System.out.println(clazz.getDeclaredAnnotation(Entry.class));
//        // 调用方法
//        Entry entry= (Entry) clazz.getDeclaredAnnotation(Entry.class);
//        System.out.println(entry.value());
    }
}
