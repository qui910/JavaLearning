package com.prd.concurrent.base;

import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

/**
 * 线程停止
 */
public class ThreadStopTest {

    public static void main(String[] args) {
//        rigthStopThreadNoBlock();
//        rigthStopThreadBlock();
//        rigthStopThreadBlockEveryLoop();
//        catInterrupt();
//        theSecondWayToStopThread();
        wangStopThreadByVolatile();
    }


    /**
     * 无sleep或wait等阻塞时停止线程,
     * 需要在线程内部主动判断中断状态
     */
    private static void rigthStopThreadNoBlock() {
        Thread test = new Thread(() -> {
            int num = 0;
            // 影响中断
            while (!Thread.currentThread().isInterrupted() &&
                    num < Integer.MAX_VALUE) {
                if (num % 10000 == 0) {
                    System.out.println(num + "是10000的倍数");
                }
                num++;
            }
            System.out.println("线程执行完成");
        });
        test.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test.interrupt();
    }

    /**
     * 线程在由sleep，wait，join等造成的阻塞情况下，停止线程时
     * 阻塞方法会响应interrupt方法，并抛出InterruptedException异常
     * 结果：
     * 0是100的倍数
     * java.lang.InterruptedException: sleep interrupted
     * at java.lang.Thread.sleep(Native Method)
     * at com.prd.concurrent.base.ThreadStopTest.lambda$rigthStopThreadBlock$1(ThreadStopTest.java:56)
     * at java.lang.Thread.run(Thread.java:748)
     */
    private static void rigthStopThreadBlock() {
        Runnable run = () -> {
            int num = 0;
            try {
                while (num <= 300
                        && !Thread.currentThread().isInterrupted()) {
                    if (num % 100 == 0) {
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(run);
        thread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

    /**
     * 在执行过程中，每次循环中都会调用sleep等阻塞线程，停止线程时.
     * 这里的Thread.currentThread().isInterrupted() 就显得多余，
     * 线程停止是while循环内的sleep响应异常控制的。
     * 结果：
     * 0是100的倍数
     * 100是100的倍数
     * 200是100的倍数
     * 300是100的倍数
     * 400是100的倍数
     * java.lang.InterruptedException: sleep interrupted
     */
    private static void rigthStopThreadBlockEveryLoop() {
        Runnable run = () -> {
            int num = 0;
            try {
                while (num <= 10000) {
                    if (num % 100 == 0) {
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(run);
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

    /**
     * 如果while或for的循环中有try/catch,会导致中断失效。
     * 结果：
     * 400是100的倍数
     * java.lang.InterruptedException: sleep interrupted
     * at java.lang.Thread.sleep(Native Method)
     * at com.prd.concurrent.base.ThreadStopTest.lambda$catInterrupt$3(ThreadStopTest.java:129)
     * at java.lang.Thread.run(Thread.java:748)
     * 500是100的倍数
     * 600是100的倍数
     * <p>
     * try/catch会拦截处理掉InterruptedException,并且中断状态判断失效
     */
    private static void catInterrupt() {
        Runnable run = () -> {
            int num = 0;
            while (num <= 10000) {
                // 这里的判断状态无效
                if (num % 100 == 0
                        && !Thread.currentThread().isInterrupted()) {
                    System.out.println(num + "是100的倍数");
                }
                num++;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // 抛出异常后，导致中断状态被清除，故而Thread.currentThread().isInterrupted()
                    //失效
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(run);
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

    /**
     * 在生成环境中，中断线程最佳实践：catche了InterruptedException之后的优先选择：
     * 在方法签名中抛出异常。那么在run()就会强制try/catch。
     * 因为在run方法中只能catch，不能再往外抛出任何异常，因为run方法是重写的，在接口中run方法的定义中本来就没有抛出异常。
     * 重写方法是不能破坏父类或接口方法的定义的。
     */
    private static void theFirstWayToStopThread() {
        Runnable runA = () -> {
            while (true) {
                System.out.println("生产开始");
                try {
                    throwInMethod();
                } catch (InterruptedException e) {
                    // 保存日志，停止程序
                    System.out.println("保存日志");
                    e.printStackTrace();
                }
            }
        };
        Thread test1 = new Thread(runA);
        test1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test1.interrupt();
    }

    /**
     * 实际的生成方法
     */
    private static void throwInMethod() throws InterruptedException {
        Thread.sleep(2000);
    }

    /**
     * 在生产环境中，中断线程最佳实践2：在catch子句中调用Thread.currentThread().interrupt()
     * 来恢复设置中断状态，以便于在后续的执行中，依然能否检查到刚才发生了中断。
     */
    private static void theSecondWayToStopThread() {
        Runnable runA = () -> {
            while (true) {
                System.out.println("生产开始");
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Interrupted 程序运行结束");
                    break;
                }
                reInterrupt();
            }
        };
        Thread test1 = new Thread(runA);
        test1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test1.interrupt();
    }

    /**
     * 实际的生成方法
     */
    private static void reInterrupt() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    private volatile static boolean canceled = false;

    /**
     * 错误方法：使用volatile变量来控制线程中断.
     * 结论：貌似看起来可行。
     * 但是如果线程是阻塞状态时，volatile并不会起作用
     */
    private static void wangStopThreadByVolatile() {
        new Thread(() -> {
            int num = 0;
            try {
                while (num <= 10000 && !canceled) {
                    if (num%100 == 0) {
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                    // 如果sleep时间过长，在阻塞状态时来改变canceled是无效的
                    // 如果这是wait，则会导致线程长时间阻塞，无法中断了
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        canceled = true;
    }
}
