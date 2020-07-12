package com.prd.concurrent;

/**
 * 对象发布逸出测试
 * 隐式逸出-注册监听事件
 * 这里使用观察者模式
 */
public class ThreadSafeTest2 {

    /**
     * 观察者模式
     */
    static class MySource {
        private EventListener listener;

        /**
         * 注册监听器
         * @param eventListener
         */
        void registerListener(EventListener eventListener) {
            this.listener = eventListener;
        }

        /**
         * 发送事件
         * @param e
         */
        void eventCome(Event e) {
            if (listener != null) {
                listener.onEvent(e);
            } else {
                System.out.println("还未初始化完毕");
            }
        }
    }

    /**
     * 事件监听器接口
     */
    interface EventListener {
        void onEvent(Event e);
    }

    /**
     * 事件接口
     */
    interface Event {
    }

    class MyEventTest {
         int count;

        public MyEventTest(MySource source) {
            // 这里注册监听器，实际在EventListener内部类中
            // 就隐式的包含了外部类MyEventTest的引用，所以在内部类中
            // 修改或查询count值都是不安全的。因为count还未初始化完成
            source.registerListener(new EventListener() {
                @Override
                public void onEvent(Event e) {
                    System.out.println("数字count为:"+count);
                }
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count = 100;
        }
    }

    /**
     * 测试错误的模式
     * 结果：数字count为:0
     */
    private void testErrorModel() {
        MySource mySource = new MySource();
        // 触发事件
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mySource.eventCome(new Event() {
                });
            }
        }).start();
        MyEventTest eventTest = new MyEventTest(mySource);
    }

    public static void main(String[] args) {
        ThreadSafeTest2 test2 = new ThreadSafeTest2();
        test2.testErrorModel();
    }
}
