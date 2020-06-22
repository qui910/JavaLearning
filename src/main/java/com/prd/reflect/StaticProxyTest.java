package com.prd.reflect;

/**
 * @author prd
 * @version V1.0
 * @Description 静态代理测试
 * @date 2020-06-11 11:40
 */
public class StaticProxyTest {
    public static void main(String[] args) {
        StaticLogProxyHandler handler = new StaticLogProxyHandler(new StudyService());
        handler.exec();
        StaticLogProxyAHandler handler1 = new StaticLogProxyAHandler(new WorkService());
        handler1.doing();
    }
}
