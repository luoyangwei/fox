package com.fox.message;

import com.fox.register.ConfigurableLoadClasses;

/**
 * <p>
 * 接收器
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/11/16 package: com.fox.message
 */
public class Receiver {

    private ConfigurableLoadClasses configurableLoadClasses;
    private Message message;


    private Receiver() {
    }

    /**
     * @param content 消息
     */
    public Receiver(String content, ConfigurableLoadClasses configurableLoadClasses) {
        this.configurableLoadClasses = configurableLoadClasses;
        convert(content);
    }


    protected Message convert(String content) {
        return new IMessage(content, configurableLoadClasses);
    }
}
