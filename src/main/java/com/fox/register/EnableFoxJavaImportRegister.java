package com.fox.register;

import com.fox.FoxJavaBootstrap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

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
public class EnableFoxJavaImportRegister extends FoxJavaBootstrap implements ApplicationListener<ApplicationReadyEvent> {


    private final ServerConfigurationProperties serverConfigurationProperties;


    @Autowired
    public EnableFoxJavaImportRegister(ServerConfigurationProperties serverConfigurationProperties) {
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
