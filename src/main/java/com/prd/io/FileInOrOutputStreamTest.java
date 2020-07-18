package com.prd.io;

import org.junit.Test;

import java.io.*;

public class FileInOrOutputStreamTest {
    private final  static String FILE_NAME="tempfile.txt";


    /**
     * 输出二进制文件，
     * （1）如果直接输出字节码的话，文本工具无法识别就是乱码
     * （2）这里如果是输出中文的字节数组的话，用txt工具还是可以正常打开。
     * （3）如果输出中文字节数组为pdf，则pdf无法打开，强行用文本是可以查看的。说明文本工具打开文件时会自动识别内部编码
     * @throws IOException
     */
    @Test
    public void test04() throws IOException {
        byte[] bytes = {12,21,34,11,21};
//        byte[] bytes = "三个地方广东省发鬼地方个1".getBytes();
        System.out.println(new File("").getAbsolutePath());
        FileOutputStream fileOutputStream = new FileOutputStream(new File("").getAbsolutePath()+"/test.txt");
        // 写入二进制文件，直接打开会出现乱码
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }

    @Test
    public void testWrite() {
        try(
            FileOutputStream out = new FileOutputStream(FILE_NAME,true);
        ){
            out.write("三个地方广东省发鬼地方个1".getBytes("UTF-8"));
            //Unix下的换行符为"\n"
            out.write("\n".getBytes());

            out.write("逗号分隔和法规和2".getBytes("UTF-8"));
            //Windows下的换行符为"\r\n"
            out.write("\r\n".getBytes());

            out.write("鬼地方个地方回复挂号费3".getBytes("UTF-8"));
            //推荐使用，具有良好的跨平台性
            out.write(System.lineSeparator().getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRead() {
        try(
           // 这里要处理换行和中文的话，最好还是通过BufferedReader
           // 自己处理换行和中文非常麻烦
           FileInputStream in = new FileInputStream(FILE_NAME);
           InputStreamReader inr = new InputStreamReader(in,"UTF-8");
           BufferedReader br = new BufferedReader(inr);
        )
        {
            String line;
            while((line=br.readLine())!=null) {
                // 这里打印换行要使用println，使用printf会导致没有换行
                // 说明BufferedReader读取一行数据时，并没有将换行读出来。
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // --->做了一些测试，不使用缓冲对文件复制时间的影响，文件的复制实质还是文件的读写。缓冲流是处理流，是对节点流的装饰。
    public void  test12() throws IOException {
        // 输入和输出都使用缓冲流
        FileInputStream in = new FileInputStream("E:\\视频资料\\大数据原理与应用\\1.1大数据时代.mp4");
        BufferedInputStream inBuffer = new BufferedInputStream(in);
        FileOutputStream out = new FileOutputStream("1.1大数据时代.mp4");
        BufferedOutputStream outBuffer = new BufferedOutputStream(out);
        int len = 0;
        byte[] bs = new byte[1024];
        long begin = System.currentTimeMillis();
        while ((len = inBuffer.read(bs)) != -1) {
            outBuffer.write(bs, 0, len);
        }
        System.out.println("复制文件所需的时间：" + (System.currentTimeMillis() - begin)); // 平均时间约 200 多毫秒
        inBuffer.close();
        in.close();
        outBuffer.close();
        out.close();
    }


    public void  test13() throws IOException {
        // 只有输入使用缓冲流
        FileInputStream in = new FileInputStream("E:\\视频资料\\大数据原理与应用\\1.1大数据时代.mp4");
        BufferedInputStream inBuffer = new BufferedInputStream(in);
        FileOutputStream out = new FileOutputStream("1.1大数据时代.mp4");
        int len = 0;
        byte[] bs = new byte[1024];
        long begin = System.currentTimeMillis();
        while ((len = inBuffer.read(bs)) != -1) {
            out.write(bs, 0, len);
        }
        System.out.println("复制文件所需时间：" + (System.currentTimeMillis() - begin)); // 平均时间约 500 多毫秒
        inBuffer.close();
        in.close();
        out.close();
    }

    public void test14() throws IOException {
        // 输入和输出都不使用缓冲流
        FileInputStream in = new FileInputStream("E:\\视频资料\\大数据原理与应用\\1.1大数据时代.mp4");
        FileOutputStream out = new FileOutputStream("1.1大数据时代.mp4");
        int len = 0;
        byte[] bs = new byte[1024];
        long begin = System.currentTimeMillis();
        while ((len = in.read(bs)) != -1) {
            out.write(bs, 0, len);
        }
        System.out.println("复制文件所需时间：" + (System.currentTimeMillis() - begin)); // 平均时间 700 多毫秒
        in.close();
        out.close();
    }


    public void test15() throws IOException {
        // 不使用缓冲
        FileInputStream in = new FileInputStream("E:\\视频资料\\大数据原理与应用\\1.1大数据时代.mp4");
        FileOutputStream out = new FileOutputStream("1.1大数据时代.mp4");
        int len = 0;
        long begin = System.currentTimeMillis();
        while ((len = in.read()) != -1) {
            out.write(len);
        }
        System.out.println("复制文件所需时间：" + (System.currentTimeMillis() - begin)); // 平均时间约 160000 毫秒，约 2 分多钟
        in.close();
        out.close();
    }
}
