package com.fox.message.converter;

import com.alibaba.fastjson.JSONObject;
import com.fox.message.MessageTemplate;
import com.fox.message.recognizer.TypesMessageRecognizer;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 默认的消息转换器
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/8 package: com.fox.message
 */
public class DefaultSimpleMessageConverter extends PreMessageConverter {


    private TypesMessageRecognizer typesMessageRecognizer;


    public void setTypesMessageRecognizer(String event) {
        this.typesMessageRecognizer = getTypesMessageRecognizer(event);
    }


    public void setTypesMessageRecognizer(TypesMessageRecognizer typesMessageRecognizer) {
        this.typesMessageRecognizer = typesMessageRecognizer;
    }


    @Override
    public void convert(String message) {

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


        } catch (Exception e) {

            // 发送的消息是异常消息

        }

    }


}
