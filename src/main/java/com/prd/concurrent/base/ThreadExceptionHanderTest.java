package com.prd.concurrent.base;

/**
 * 测试多线程情况下，子线程异常的处理
 */
public class ThreadExceptionHanderTest {



    public static void main(String[] args) {

        new ThreadExceptionHanderTest().testPreHandler();
    }

    /**
     * 设置全局处理器和单个线程独自设置都可以
     */
    private void testPreHandler() {
        // 全局处理器
        //Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHanlder());
        Thread thread = new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            throw  new RuntimeException();
        });
        MyUncaughtExceptionHanlder th= new MyUncaughtExceptionHanlder();
        // 线程独自处理器
        thread.setUncaughtExceptionHandler(th);
        thread.start();
    }

    class MyUncaughtExceptionHanlder implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("线程异常，终止"+t.getName());
        }
    }
}
