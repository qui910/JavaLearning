package com.prd.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetAddress;

public class NettyHelloServer {

    public void bind(int port) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childHandler(new ChildChannelHandler());

            ChannelFuture channelFuture = bootstrap.bind(port).sync();

            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ChannelPipeline pipeline = ch.pipeline();

            // 以("\n")为结尾分割的 解码器
            pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));

            // 字符串解码 和 编码
            pipeline.addLast("decoder", new StringDecoder());
            pipeline.addLast("encoder", new StringEncoder());

            // 自己的逻辑Handler
            pipeline.addLast("handler", new HelloServerHandler());
        }
    }

    private class HelloServerHandler extends SimpleChannelInboundHandler<String> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
            // 收到消息直接打印输出
            System.out.println(ctx.channel().remoteAddress() + " Say : " + msg);

            // 返回客户端消息 - 我已经接收到了你的消息
            ctx.writeAndFlush("Received your message !\n");
        }

        /**
         * 覆盖 channelActive 方法 在channel被启用的时候触发 (在建立连接的时候)
         * channelActive 和 channelInActive 在后面的内容中讲述，这里先不做详细的描述
         */
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("RamoteAddress : " + ctx.channel().remoteAddress() + " active !");
            ctx.writeAndFlush( "Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n");
            super.channelActive(ctx);
        }
    }

    public static void main(String[] args) {
        try {
            new NettyHelloServer().bind(8080);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
