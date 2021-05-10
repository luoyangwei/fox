package com.fox.demo.test;

import com.fox.FoxActivityBootstrap;
import com.fox.configuration.BootstrapConfigurableProperties;
import com.fox.configuration.ScanActivityProperties;

/**
 * <p>
 * 注解配置
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/10 package: com.fox.demo.activity
 */
public class BootstrapTest {


    public static void main(String[] args) {

        BootstrapConfigurableProperties bootstrapConfigurableProperties = new BootstrapConfigurableProperties();
        bootstrapConfigurableProperties.setAddress("127.0.0.1");
        bootstrapConfigurableProperties.setPort(2542);
        ScanActivityProperties scanActivityProperties = new ScanActivityProperties();
        scanActivityProperties.setBasePackages("com.fox");
        FoxActivityBootstrap bootstrap = new FoxActivityBootstrap(bootstrapConfigurableProperties, scanActivityProperties);
    }

}
