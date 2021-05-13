package com.fox.handler;

import com.fox.message.converter.DefaultMessageConverter;
import com.fox.message.converter.MessageConverter;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * <p>
 * 默认的handler
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/6 package: com.fox.handler
 */
@Slf4j
public class DefaultSimpleChannelHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    private final MessageConverter<Object> messageConverter = new DefaultMessageConverter<>();


    public DefaultSimpleChannelHandler() {
    }


    /**
     * 用于记录和管理所有客户端的channel
     */
    private final static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        // 客户端传递过来的消息
        String content = textWebSocketFrame.text();
        System.out.println("接收到了客户端的消息是:" + content);

        messageConverter.convert(content);

        // 将客户端发送过来的消息刷到所有的channel中
        for (Channel channel : clients) {
            channel.writeAndFlush(
                    new TextWebSocketFrame("[服务器接收到了客户端的消息:]" + LocalDateTime.now() + ",消息为:" + content));
        }
    }


    @Deprecated
    private void registerRecognizer() {
        // TODO 删除
    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("handlerAdded: {}", ctx.channel().id().asLongText());
        clients.add(ctx.channel());
    }


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("handlerRemoved: {}", ctx.channel().id().asLongText());
        clients.remove(ctx.channel());
    }

}
