package com.fox.handler;

import com.fox.message.IMessage;
import com.fox.message.Message;
import com.fox.message.Receiver;
import com.fox.register.ConfigurableLoadClasses;
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
public class DefaultChannelMessageHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    private ConfigurableLoadClasses configurableLoadClasses;

    public DefaultChannelMessageHandler(ConfigurableLoadClasses configurableLoadClasses) {
        this.configurableLoadClasses = configurableLoadClasses;
    }


    /**
     * 用于记录和管理所有客户端的channel
     * 目前的持久化方案只会存在于内容中，如果服务器down机或者内存出现问题将影响框架的正常运行，这并不是最终的解决方案
     * 所以这里后续会有优化，但不是现在，这里先记录下来优化方案
     * <p>
     * 1.持久化储存：目前市面上用于消息丢失，或者数据丢失的持久化储存方案可以借鉴的有MQ的持久化存储方案，或者是Redis的AOF持久化文件到服务器上
     * 2.让框架使用者去管理这些通道的储存
     * 3.把通道的信息储存到Redis或者数据库（有待思考是否需要这样）
     */
    private final static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        // 客户端传递过来的消息
        Receiver receiver = new Receiver(textWebSocketFrame.text(), configurableLoadClasses);
//        receiver.convert(content);


        // 将客户端发送过来的消息刷到所有的channel中
//        for (Channel channel : clients) {
//            channel.writeAndFlush(
//                    new TextWebSocketFrame("[服务器接收到了客户端的消息:]" + LocalDateTime.now() + ",消息为:" + content));
//        }
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
