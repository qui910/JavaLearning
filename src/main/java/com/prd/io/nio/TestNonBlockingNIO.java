package com.prd.io.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;

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
 *  本例完成的是非阻塞式IO
 */
public class TestNonBlockingNIO {

    //客户端
    @Test
    public void client() throws IOException {
        // 1 获取通道
        SocketChannel channel=SocketChannel.open(
                new InetSocketAddress("127.0.0.1",9898));

        // 2 切换成非阻塞模式
        channel.configureBlocking(false);

        // 3 分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        // 4 发送数据给服务器
        buf.put(new Date().toString().getBytes());
        buf.flip();
        channel.write(buf);
        buf.clear();

        // 5 关闭通道
        channel.close();
    }

    //服务器
    @Test
    public void server() throws IOException {
        // 1 获取通道
        ServerSocketChannel socketChannel = ServerSocketChannel.open();

        // 2 切换为非阻塞模式
        socketChannel.configureBlocking(false);

        // 3 绑定连接端口
        socketChannel.bind(new InetSocketAddress(9898));

        // 4 获取选择器
        Selector selector = Selector.open();

        // 5 将通道注册到选择器,并且指定“监听接收事件”
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 6 轮询式的获取选择器上已经”准备就绪“的事件
        while (selector.select()>0) {
            // 7 获取当前选择器中所有注册的“选择键（已就绪的监听事件）”
           Iterator<SelectionKey> it=  selector.selectedKeys().iterator();

           while(it.hasNext()) {
               // 8 获取准备“就绪”的事件
               SelectionKey sk = it.next();

               // 9 判断具体是什么事件准备就绪
               if (sk.isAcceptable()) {
                   //10 若接收就绪，获取客户端连接
                   SocketChannel sChannel = socketChannel.accept();

                   // 11 切换非阻塞模式
                   sChannel.configureBlocking(false);

                   // 12 将通道注册到选择器上
                   sChannel.register(selector,SelectionKey.OP_READ);
               } else if(sk.isReadable()) {
                   // 13 获取当前选择器上“读就绪”状态的通道
                   SocketChannel sc = (SocketChannel) sk.channel();

                   // 14 读取数据
                   ByteBuffer buf = ByteBuffer.allocate(1024);

                   int len = 0;
                   while ((len=sc.read(buf))>0) {
                       buf.flip();
                       System.out.println(new String(buf.array(),0,len));
                       buf.clear();
                   }
               }

               // 15 操作完成后，取消选择键，否则下次循环还是会取到数据
               it.remove();
           }
        }
    }
}
