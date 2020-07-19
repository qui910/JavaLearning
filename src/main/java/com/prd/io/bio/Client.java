package com.prd.io.bio;

import org.junit.Test;

import java.io.*;
import java.net.Socket;

public class Client {

    public static final String QUIT="quit";

    public static final String DEFAULT_SERVER_HOST = "127.0.0.1";

    public static final int DEFAULT_SERVER_PORT=8888;

    @Test
    public void createClient(int i) {
        Socket socket = null;
        BufferedReader  reader = null;
        BufferedWriter writer = null;
        try {
            // 创建socket
            socket = new Socket(DEFAULT_SERVER_HOST,DEFAULT_SERVER_PORT);
            System.out.println("客户端["+socket+"]与服务器建立连接。");
            // 创建IO流
            // System.out.println("请输入信息：");
            reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));

            // 等待用户输入信息
            BufferedReader consoleReader = new BufferedReader(
                    new InputStreamReader(System.in));
            // 一直发送消息，直到从屏幕接收到quit，才退出
            while (true) {
                String input = consoleReader.readLine();

                // 发送消息给服务器
                writer.write(input+"\n");
                writer.flush();

                // 读取服务器返回消息
                System.out.println(reader.readLine());

                // 查看用户是否退出
                if (QUIT.equals(input)) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
            //这里关闭writer的话，会自动关闭它封装的OutputStreamWriter 和socket
            if (writer!=null) {
                try {
                    writer.close();
                    System.out.println("关闭client");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
