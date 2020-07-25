package com.prd.concurrent.juc;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 演示线程池测试
 */
public class ExecutorsTest {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = new ThreadPoolExecutor(1,2,
                3, TimeUnit.SECONDS,new ArrayBlockingQueue<>(1),
                new ExecutorsTest().new CoreThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        // 设置allowCoreThreadTimeout=true（默认false）时，核心线程会超时关闭
        // executor.allowCoreThreadTimeOut(true);
        for (int i=0;i<4;i++) {
            es.execute(new ExecutorsTest().new MyRunable(i));
        }

        Thread.sleep(5000);
        System.out.println("核心线程数量:"+((ThreadPoolExecutor) es).getCorePoolSize());
        System.out.println("核心线程数量:"+((ThreadPoolExecutor) es).getPoolSize());
    }

    class MyRunable implements Runnable {

        private int id;

        public MyRunable(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            for(;;) {

            }
        }
    }

    class CoreThreadFactory implements ThreadFactory {
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        CoreThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "core-pool-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }
}
