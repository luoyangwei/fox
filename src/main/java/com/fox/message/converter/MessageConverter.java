package com.fox.message.converter;

/**
 * <p>
 * 消息转换器
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/8 package: com.fox.message
 */
public interface MessageConverter<T> {


    /**
     * 消息转换
     *
     * @param message 消息内容
     *
     * @return 转换后的对象
     */
    T convert(String message);


}
