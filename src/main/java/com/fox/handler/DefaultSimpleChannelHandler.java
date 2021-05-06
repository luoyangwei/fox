package com.fox.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/**
 * <p>
 * 默认的handler
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/6 package: com.fox.handler
 */
public class DefaultSimpleChannelHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    /**
     * 用于记录和管理所有客户端的channel
     */
    private final static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        // 客户端传递过来的消息
        String content = textWebSocketFrame.text();
        System.out.println("接收到了客户端的消息是:" + content);

        // 将客户端发送过来的消息刷到所有的channel中
        for (Channel channel : clients) {
            channel.writeAndFlush(
                    new TextWebSocketFrame("[服务器接收到了客户端的消息:]" + LocalDateTime.now() + ",消息为:" + content));
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("有用户连接进来了");
        // 要给客户端发送一条消息告诉客户端连接成功了，不然客户端会直接断开
        ctx.channel().writeAndFlush("1");
        clients.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("有用户退出了");
        clients.remove(ctx.channel());
    }

}
