package com.fox.register;

import com.fox.WssServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.net.InetSocketAddress;

/**
 * <p>
 * 注册服务到Spring
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/6 package: com.fox.register
 */
@Slf4j
public class EnableFoxActivityImportRegister implements CommandLineRunner {

    private NioEventLoopGroup bossEventLoopGroup;

    private NioEventLoopGroup workEventLoopGroup;

    private ServerBootstrap serverBootstrap;

    private ChannelFuture channelFuture;

    private final ServerConfigurationProperties serverConfigurationProperties;

    @Autowired
    public EnableFoxActivityImportRegister(ServerConfigurationProperties serverConfigurationProperties) {
        this.serverConfigurationProperties = serverConfigurationProperties;
    }

    @Override
    public void run(String... args) throws Exception {
        try {

            bossEventLoopGroup = new NioEventLoopGroup();
            workEventLoopGroup = new NioEventLoopGroup();
            serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossEventLoopGroup, workEventLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(serverConfigurationProperties.getAddress(), serverConfigurationProperties.getPort()))
                    .childHandler(new WssServerInitializer());
            channelFuture = serverBootstrap.bind().sync();
            log.info("Netty服务 {}: {}", serverConfigurationProperties.getAddress(), serverConfigurationProperties.getPort());
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {

            log.error("error", e);
        } finally {
            channelFuture.channel().close();
            bossEventLoopGroup.shutdownGracefully();
            workEventLoopGroup.shutdownGracefully();
        }

    }

}
