package com.prd.concurrent;

/**
 * 演示可见性带来的问题
 *
 */
public class ThreadJMMTest1 {
    int a=1;
    int b=2;

    private void print() {
        System.out.println("b="+b+",a="+a);
    }

    private void change() {
        a=3;
        b=a;
    }

    public static void main(String[] args) {

        while(true) {
            ThreadJMMTest1 test1 = new ThreadJMMTest1();

            new Thread(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test1.change();
            }).start();

            new Thread(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test1.print();
            }).start();
        }
    }
}
