package com.fox;

import com.fox.register.EnableFoxJavaServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author luoyangwei
 */
@SpringBootApplication
@EnableFoxJavaServer
public class FoxJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoxJavaApplication.class, args);
    }

}
