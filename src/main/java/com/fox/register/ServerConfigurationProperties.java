package com.fox.register;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 《 在这里描述类的作用 》
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/6 package: com.fox.register
 */
@ConfigurationProperties("server")
@Component
public class ServerConfigurationProperties {

    private Integer port = 8080;

    private String address = "127.0.0.1";

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
