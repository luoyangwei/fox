package com.fox.register;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;

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

    @Override
    public void run(String... args) throws Exception {
        log.info("run方法调用，在这里启动Netty服务");
    }

}
