package com.prd.reflect;

import java.lang.reflect.Proxy;

/**
 * @author ruidong.pang
 * @version V1.0
 * @Description 动态代理测试
 * @date 2020-06-11 11:59
 */
public class DynamicProxyTest {
    public static void main(String[] args) {

        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        DemoInterface demoInterface = (DemoInterface) Proxy.newProxyInstance(StudyService.class.getClassLoader(),
                new Class[]{DemoInterface.class},
                new DynamicLogProxyHandler(new StudyService()));
        demoInterface.exec();

        DemoAInterface demoAInterface = (DemoAInterface) Proxy.newProxyInstance(WorkService.class.getClassLoader(),
                new Class[]{DemoAInterface.class},
                new DynamicLogProxyHandler(new WorkService()));
        demoAInterface.doing();

        // 如果接口中由多个方法，会依次代理
        DemoBInterface demoBInterface = (DemoBInterface) Proxy.newProxyInstance(MultipleMethonInterface.class.getClassLoader(),
                new Class[]{DemoBInterface.class},
                new DynamicLogProxyHandler(new MultipleMethonInterface()));
        demoBInterface.doing1();
        demoBInterface.doing2();
    }
}
