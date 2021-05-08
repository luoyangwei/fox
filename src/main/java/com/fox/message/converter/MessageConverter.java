package com.fox.message.converter;

/**
 * <p>
 * 消息转换器
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/8 package: com.fox.message
 */
public interface MessageConverter {


    /**
     * 消息转换
     *
     * @param message 消息内容
     */
    void convert(String message);


}
