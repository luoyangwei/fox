package com.fox.register;

import com.fox.FoxActivityBootstrap;
import com.fox.WssServerInitializer;
import com.fox.listener.ListenerContainer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

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
@Component
public class EnableFoxActivityImportRegister extends FoxActivityBootstrap implements ApplicationListener<ApplicationReadyEvent> {


    private final ServerConfigurationProperties serverConfigurationProperties;


    @Autowired
    public EnableFoxActivityImportRegister(ServerConfigurationProperties serverConfigurationProperties) {
        this.serverConfigurationProperties = serverConfigurationProperties;
    }


    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        ConfigurableApplicationContext configurableApplicationContext = applicationReadyEvent.getApplicationContext();
        ConfigurableListableBeanFactory configurableListableBeanFactory = configurableApplicationContext.getBeanFactory();
        // 启动
        bootstrap(serverConfigurationProperties.getAddress(), serverConfigurationProperties.getPort(), configurableListableBeanFactory);
    }


}
