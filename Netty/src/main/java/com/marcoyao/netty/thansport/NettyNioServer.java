package com.marcoyao.netty.thansport;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * Created by marcoyao on 2017/8/2.
 *
 *  Netty NIO 的代码，只是改变了一行代码，就从 OIO 传输 切换到了 NIO。
 *
 */
public class NettyNioServer {
    public void server(int port) throws Exception {

        final ByteBuf buf = Unpooled.unreleasableBuffer(
                Unpooled.copiedBuffer("Hi!\r\n", Charset.forName("UTF-8")));
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();//1 创建一个 ServerBootstrap
            b.group(new NioEventLoopGroup(), new NioEventLoopGroup()) //2 使用 NioEventLoopGroup 非阻塞模式
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {//3 .指定 ChannelInitializer 将给每个接受的连接调用
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {//4 添加的 ChannelInboundHandlerAdapter() 接收事件并进行处理
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception
                                {
                                    ctx.writeAndFlush(buf.duplicate())//5 写信息到客户端，并添加 ChannelFutureListener 当一旦消息写入就关闭连接
                                            .addListener(ChannelFutureListener.CLOSE);
                                }
                            });
                        }
                    });
            ChannelFuture f = b.bind().sync();//6 绑定服务器来接受连接
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();//7 释放所有资源
        }
    }
}
