package com.fox.message.converter;

import com.fox.message.recognizer.TypesMessageRecognizer;

/**
 * <p>
 * 默认的消息转换器
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/8 package: com.fox.message
 */
public class DefaultMessageConverter<T> extends PreMessageConverter<T> {


    @Override
    protected T wrapper(TypesMessageRecognizer typesMessageRecognizer) {
        return null;
    }

}
