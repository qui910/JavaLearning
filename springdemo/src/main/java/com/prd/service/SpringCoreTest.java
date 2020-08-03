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
        ISpringCoreDemoService service = context.getBean(ISpringCoreDemoService.class);
        service.Hello();
        service.Hello1("aaa");
        // ISpringCoreDemoService service = context.getBean(SpringCoreDemoService.class);
        // 通过这种方式会提示 Exception in thread "main" org.springframework.beans.factory.NoSuchBeanDefinitionException:
        // No qualifying bean of type 'com.prd.service.SpringCoreDemoService' available
        // 因为 开启了切面 SpringCoreDemoService也已经被动态代理了，无法通过class获取，
        // 解决方法：(1)可以使用 名称"service" 或ISpringCoreDemoService.class获取
        // (2) 或 @EnableAspectJAutoProxy(proxyTargetClass = true) 这里为true，就是CGLIB代码。默认为false
        // 时就是使用JDK的动态代理，这时会被代理为Proxy,而非SpringCoreDemoService
        // 这里就是JDK动态代理就是使用 聚合接口，而非继承目标对象。因为代理对象是从Proxy继承的，同时实现目标对象接口，所以只能
        // 是实现目标对象的接口


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
