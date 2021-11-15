package com.fox;

import com.fox.handler.DefaultChannelMessageHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Wss协议初始化
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/6 package: com.fox
 */
@Slf4j
public class WssServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        log.info(": {}", socketChannel);
        // websocket基于http协议，所以需要http编解码器
        pipeline.addLast(new HttpServerCodec());
        // 添加对于读写大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());
        // 对httpMessage进行聚合
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));

        // ================= 上述是用于支持http协议的 ==============

        // websocket 服务器处理的协议，用于给指定的客户端进行连接访问的路由地址
        // 比如处理一些握手动作(ping,pong)
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        // 自定义handler
        pipeline.addLast(new DefaultChannelMessageHandler());
    }

}
