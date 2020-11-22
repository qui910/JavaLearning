package com.prd.createspring;

import java.io.File;

/**
 * JavaLearning
 * 2020-11-20 21:17
 *
 * @author ruidong.pang
 * @since
 **/
public class Test {

    public static void main(String[] args) {

/*        BeanFactory beanFactory = new BeanFactory("myspring.xml");

        UserService service = (UserService) beanFactory.getBean("service");
        service.find();*/

        AnnotationConfigApplicationContext context = new
                AnnotationConfigApplicationContext();
        context.scan("com.prd.createspring");
        UserService service = (UserService) context.getBean("service");
        System.out.println(service.getClass().getName());
    }
}
