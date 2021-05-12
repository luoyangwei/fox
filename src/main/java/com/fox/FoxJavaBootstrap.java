package com.fox;

import com.fox.configuration.BootstrapConfigurableProperties;
import com.fox.configuration.ScanActivityProperties;
import com.fox.register.ConfigurableLoadClasses;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.net.InetSocketAddress;

/**
 * <p>
 * 启动类
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/10 package: com.fox
 */
@Slf4j
public class FoxJavaBootstrap {

    private NioEventLoopGroup bossEventLoopGroup;
    private NioEventLoopGroup workEventLoopGroup;
    private ChannelFuture channelFuture;

    private BootstrapConfigurableProperties bootstrapConfigurableProperties = null;
    private ScanActivityProperties scanActivityProperties = null;


    public FoxJavaBootstrap() {
        // 加载配置的包所有的类
    }

    public FoxJavaBootstrap(BootstrapConfigurableProperties bootstrapConfigurableProperties, ScanActivityProperties scanActivityProperties) {
        this.bootstrapConfigurableProperties = bootstrapConfigurableProperties;
        this.scanActivityProperties = scanActivityProperties;
        bootstrap();
    }

    /**
     * Spring 启动类
     *
     * @param address                         注册地址
     * @param port                            注册端口
     * @param configurableListableBeanFactory bean工厂
     */
    protected void bootstrap(String address, int port, ConfigurableListableBeanFactory configurableListableBeanFactory) {
        // 注册监听器
        bootstrap(address, port);
    }


    /**
     * 不使用Spring的启动方式
     */
    protected void bootstrap() {

        ConfigurableLoadClasses configurableLoadClasses = new ConfigurableLoadClasses(scanActivityProperties.getBasePackages());
        configurableLoadClasses.scanFiles(scanActivityProperties.getBasePackages());

        // 注册Activity
        registerActivity(configurableLoadClasses);

        // 启动
        bootstrap(bootstrapConfigurableProperties.getAddress(), bootstrapConfigurableProperties.getPort());
    }


    protected void registerActivity(ConfigurableLoadClasses configurableLoadClasses) {

    }


    /**
     * 启动方法
     *
     * @param address 注册地址
     * @param port    注册端口
     */
    protected void bootstrap(String address, int port) {

        try {

            bossEventLoopGroup = new NioEventLoopGroup();
            workEventLoopGroup = new NioEventLoopGroup();
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossEventLoopGroup, workEventLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(address, port))
                    .childHandler(new WssServerInitializer());
            channelFuture = serverBootstrap.bind().sync();
            log.info("{}:{}", address, port);
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
