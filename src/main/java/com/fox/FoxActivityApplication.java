package com.fox;

import com.fox.register.EnableFoxActivityServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author luoyangwei
 */
@SpringBootApplication
@EnableFoxActivityServer
public class FoxActivityApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoxActivityApplication.class, args);
    }

}
