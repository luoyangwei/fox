package com.fox.message.recognizer;

import com.fox.message.converter.PreMessageConverter;

/**
 * <p>
 * 事件识别器
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/8 package: com.fox.message
 */
public class EventMessageRecognizer implements TypesMessageRecognizer {


    private final String eventMessageEventType = "event_message";


    @Override
    public String getType() {
        return this.eventMessageEventType;
    }


    @Override
    public String getMessageContentType() {
        return null;
    }

}
