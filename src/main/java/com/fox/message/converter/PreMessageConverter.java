package com.fox.message.converter;

import com.fox.message.converter.MessageConverter;
import com.fox.message.recognizer.TypesMessageRecognizer;

import java.util.List;

/**
 * <p>
 * 前置消息识别器
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/8 package: com.fox.message
 */
public abstract class PreMessageConverter implements MessageConverter {


    /**
     * 消息识别器
     */
    private List<TypesMessageRecognizer> typesMessageRecognizers;


    /**
     * 查找事件类型识别器
     *
     * @param event 事件类型
     *
     * @return {@link TypesMessageRecognizer}
     */
    public TypesMessageRecognizer getTypesMessageRecognizer(String event) {
        TypesMessageRecognizer typesMessageRecognizer = null;
        for (TypesMessageRecognizer recognizer : typesMessageRecognizers) {
            if (recognizer.getType().equals(event)) {
                typesMessageRecognizer = recognizer;
            }
        }

        if (typesMessageRecognizer == null) {
            throw new IllegalArgumentException(String.format("Event %s was not found", event));
        }

        return typesMessageRecognizer;
    }

}
