package com.fox.configuration;

/**
 * <p>
 * 启动配置
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/10 package: com.fox.configuration
 */
public class BootstrapConfigurableProperties {


    private String address;


    private Integer port;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
