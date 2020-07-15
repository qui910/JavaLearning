package com.prd.concurrent.base;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 线程间管道通信测试
 */
public class ThreadPipIOTest {

    private static class Writer extends Thread {

        private PipedOutputStream out;

        public Writer(String name, PipedOutputStream out) {
            super(name);
            this.out = out;
        }

        @Override
        public void run() {
            System.out.println("线程"+getName()+"进入");
            try {
                Thread.sleep(6000);
                System.out.println("线程"+getName()+"开始运行");
                for (int i=0;i<10;i++) {
                    out.write(("线程"+getName()+"输出数据到Reader"+i).getBytes());
                    //out.write("\n".getBytes());
                    out.flush();
                    System.out.println("线程"+getName()+"状态"+i+getState().name());
                    Thread.sleep(1000);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally{
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private static class Reader extends Thread {

        private PipedInputStream in;

        public Reader(String name, PipedInputStream in) {
            super(name);
            this.in = in;
        }

        @Override
        public void run() {
            System.out.println("线程"+getName()+"进入");
            int ch=0;
            byte[] bytes = new byte[1024];
            try {
                System.out.println("线程"+getName()+"开始运行");
                // 进入线程后，因为要等待输出流准备完毕，这里被输入流阻塞
                while((ch = in.read(bytes)) !=-1){
                    byte[] bytestmp = new byte[ch];
                    System.arraycopy(bytes,0,bytestmp,0,ch);
                    System.out.println("线程"+getName()+"状态"+getState().name());
                    System.out.println("线程"+getName()+"读取数据:"+new String(bytestmp));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally{
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        PipedOutputStream out = new PipedOutputStream();
        PipedInputStream in = new PipedInputStream(out);
        Writer writer = new Writer("A",out);
        Reader reader = new Reader("B",in);

        // 因为这里的输入流是通过输出流创建的，虽然reader线程先执行，
        // 但是只要out不关闭，则reader就会阻塞，一致到有数据送来。
        reader.start();
        Thread.sleep(1000);
        writer.start();
        Thread.sleep(1000);
        System.out.println("线程"+reader.getName()+"状态为："+reader.getState().name());
        System.out.println("线程"+writer.getName()+"状态为："+writer.getState().name());
    }
}
