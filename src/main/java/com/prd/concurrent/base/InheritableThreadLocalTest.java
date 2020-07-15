package com.prd.concurrent.base;

/**
 * InheritableThreadLocal 测试
 *
 */
public class InheritableThreadLocalTest {
    private static ThreadLocal<String> localVariable = new ThreadLocal();

    private static InheritableThreadLocal<String> inheritableLocalVariable = new InheritableThreadLocal();

    public static void main(String[] args) {
        localVariable.set("main thread");
        inheritableLocalVariable.set("main thread");
        System.out.println("main localVariable is "+localVariable.get());
        System.out.println("main inheritableLocalVariable is "+inheritableLocalVariable.get());

        testThreadLocal();

        testInheritableThreadLocal();
    }

    public static void testThreadLocal() {
        new Thread(()->{
            System.out.println("sub localVariable is "+localVariable.get());
        }).start();
    }

    public static void testInheritableThreadLocal() {
        new Thread(()->{
            System.out.println("sub inheritableLocalVariable is "+inheritableLocalVariable.get());
        }).start();
    }
}
