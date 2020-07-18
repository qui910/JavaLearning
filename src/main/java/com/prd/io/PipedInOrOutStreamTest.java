package com.prd.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedInOrOutStreamTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        PipedOutputStream out = new PipedOutputStream();
        PipedInputStream in = new PipedInputStream(out);

        Writers writers = new Writers("W",out);
        Readers readers = new Readers("R",in);
        writers.start();
        Thread.sleep(1000);
        readers.start();
    }


    /**
     * bytes数组大小必须能否保存下一次传送的所有中文，否则会因为
     * 接收不完整的中文编码导致乱码
     */
    private static class Readers extends Thread {
        PipedInputStream in;

        public Readers(String name, PipedInputStream in) {
            super(name);
            this.in = in;
        }

        @Override
        public void run() {
            byte[] bytes = new byte[1200];
            int ch=0;
            int num=0;
            try {
                while (ch!=-1) {
                    ch=in.read(bytes);
                    if (ch!=-1) {
                        System.out.println("取得：" + new String(bytes, "UTF-8"));
                    }
                    num = num + ch;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("总共接收字符："+num);
        }
    }

    private static class Writers extends Thread {
        PipedOutputStream out;

        public Writers(String name, PipedOutputStream out) {
            super(name);
            this.out = out;
        }

        @Override
        public void run() {
            String msg = null;
            try {
                for(int j=0;j<200;j++) {
                    msg = "我是你胜多负少的风格" + j;
                    out.write(msg.getBytes("UTF-8"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
