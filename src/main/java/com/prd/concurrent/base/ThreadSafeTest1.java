package com.prd.concurrent.base;

/**
 * 对象发布逸出问题
 * 初始化未完毕，就this赋值
 */
public class ThreadSafeTest1 {

    static Point point;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            try {
                new Point(1,2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(50);
        if (point!=null) {
            System.out.println(point.toString());
        }
    }

    static class Point{
        private final int x,y;
        public Point(int x, int y) throws InterruptedException {
            this.x = x;
            ThreadSafeTest1.point=this;
            Thread.sleep(100);
            this.y = y;
        }
        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}