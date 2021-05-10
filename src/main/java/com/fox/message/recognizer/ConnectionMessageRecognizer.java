package com.fox.message.recognizer;

import com.fox.message.ConnectionMessage;

/**
 * <p>
 * 连接消息内容识别
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/8 package: com.fox.message
 */
public class ConnectionMessageRecognizer implements TypesMessageRecognizer {

    private final String connectionMessageEventType = "connection_message";


    @Override
    public String getType() {
        return this.connectionMessageEventType;
    }


    @Override
    public Class<?> getMessageContentType() {
        return ConnectionMessage.class;
    }

}
