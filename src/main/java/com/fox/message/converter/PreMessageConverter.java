package com.fox.message.converter;

import com.alibaba.fastjson.JSONObject;
import com.fox.message.MessageTemplate;
import com.fox.message.recognizer.TypesMessageRecognizer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前置消息识别器
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/8 package: com.fox.message
 */
@Slf4j
public abstract class PreMessageConverter<T> implements MessageConverter<T> {


    /**
     * 消息识别器集合
     */
    private final static List<TypesMessageRecognizer> recognizers = new ArrayList<>();


    /**
     * 找出来的识别器
     */
    private TypesMessageRecognizer typesMessageRecognizer;


    /**
     * 设置识别器
     *
     * @param event 事件
     */
    public void setTypesMessageRecognizer(String event) {
        this.typesMessageRecognizer = getTypesMessageRecognizer(event);
    }


    /**
     * 设置识别器
     *
     * @param typesMessageRecognizer 识别器对象
     */
    public void setTypesMessageRecognizer(TypesMessageRecognizer typesMessageRecognizer) {
        this.typesMessageRecognizer = typesMessageRecognizer;
    }


    /**
     * 注册识别器
     *
     * @param typesMessageRecognizer 识别器
     */
    public void registerTypesMessageRecognizer(TypesMessageRecognizer typesMessageRecognizer) {
        // 注册识别器到集合
        recognizers.add(typesMessageRecognizer);
    }


    /**
     * 查找事件类型识别器
     *
     * @param event 事件类型
     *
     * @return {@link TypesMessageRecognizer}
     */
    public TypesMessageRecognizer getTypesMessageRecognizer(String event) {
        TypesMessageRecognizer typesMessageRecognizer = null;
        for (TypesMessageRecognizer recognizer : recognizers) {
            if (recognizer.getType().equals(event)) {
                typesMessageRecognizer = recognizer;
            }
        }

        if (typesMessageRecognizer == null) {
            throw new IllegalArgumentException(String.format("Event %s was not found", event));
        }

        return typesMessageRecognizer;
    }


    @Override
    public T convert(String message) {

        try {

            // 转换消息为消息模板
            MessageTemplate messageTemplate = JSONObject.parseObject(message).toJavaObject(MessageTemplate.class);

            String event = messageTemplate.getEvent();
            if (!StringUtils.hasText(event)) {

                // 很明显的错误事件消息
                throw new IllegalArgumentException("Obvious error event message");
            }

            // 设置消息识别器
            setTypesMessageRecognizer(event);
            return wrapper(typesMessageRecognizer);

        } catch (Exception e) {

            // 发送的消息是异常消息
            log.error("Convert message fail", e);
        }
        return null;
    }


    /**
     * 包装成需要的对象
     *
     * @param typesMessageRecognizer 消息识别器
     *
     * @return class
     */
    protected abstract T wrapper(TypesMessageRecognizer typesMessageRecognizer);

}
