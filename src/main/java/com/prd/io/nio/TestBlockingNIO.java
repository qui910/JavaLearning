package com.prd.io.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 使用NIO 完成网络通信的三个核心
 * （1）通道（Channel）:负责连接
 *    java.nio.channels.Channel 接口：
 *        |--SelectableChannel
 *            |--SocketChannel
 *            |--ServerSocketChannel
 *            |--DatagramChannel
 * 注意：FileChannel是无法切换为非阻塞式的。即选择器无法监控。
 *            |--Pipe.SinkChannel
 *            |--Pipe.SourceChannel
 * （2）缓冲区（Buffer）:负责数据的存取
 *
 * （3）选择器（Selector）:是SelectableChannel的多路复用器，用于监控SelectableChannel的IO状况
 *
 *  本例完成的还是阻塞式IO
 */
public class TestBlockingNIO {

    //客户端
    @Test
    public void client() throws IOException {
        // 这里演示使用1.7的open方式打开通道

        // 1 获取通道
        SocketChannel channel=SocketChannel.open(
                new InetSocketAddress("127.0.0.1",9898));

        // 2 分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        // 3 读取本地文件，并发送到服务器
        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        while (inChannel.read(buf)!=-1) {
            buf.flip();
            channel.write(buf);
            buf.clear();
        }

        // 这里要添加次方法，告诉服务端已经送数据完成，否则服务端会一直阻塞到接收图片数据中
        // 这既是阻塞IO
        channel.shutdownOutput();

        // 4 接收服务器端的反馈
        int len=0;
        while((len=channel.read(buf))!=-1) {
            buf.flip();
            System.out.println(new String(buf.array(),0,len));
            buf.clear();
        }

        // 5 关闭通道
        channel.close();
        inChannel.close();
    }

    // 服务器
    @Test
    public void server() throws IOException {
        // 1 获取通道
        ServerSocketChannel socketChannel = ServerSocketChannel.open();

        // 2 绑定连接端口
        socketChannel.bind(new InetSocketAddress(9898));

        // 3 获取客户端连接的通道  阻塞等待
        SocketChannel schannel = socketChannel.accept();

        // 4 分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        // 5 接收客户端数据，并保存到本地
        // 如果客户端不shutdown，则此处会一直阻塞。
        FileChannel outChannel = FileChannel.open(Paths.get("3.jpg"),StandardOpenOption.WRITE,StandardOpenOption.CREATE);
        while(schannel.read(buf)!=-1) {
            buf.flip();
            outChannel.write(buf);
            buf.clear();
        }
        System.out.println("接收图片成功");

        // 6 发送反馈到客户端
        buf.put("服务端接收数据成功".getBytes());
        buf.flip();
        schannel.write(buf);
        buf.clear();


        // 7 关闭通道
        schannel.close();
        outChannel.close();
        socketChannel.close();
    }
}
