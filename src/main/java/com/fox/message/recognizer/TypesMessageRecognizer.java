package com.fox.message.recognizer;

/**
 * <p>
 * 消息类型识别器
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/8 package: com.fox.message
 */
public interface TypesMessageRecognizer {


    /**
     * 获取事件类型
     *
     * @return event
     */
    String getType();


    /**
     * 消息内容
     *
     * @return type
     */
    Class<?> getMessageContentType();



}
