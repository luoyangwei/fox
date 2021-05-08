package com.fox.message;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 同意消息的模板
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/8 package: com.fox.message
 */
@Data
@Builder
public class MessageTemplate implements Serializable {

    private static final long serialVersionUID = -9051333174894830361L;


    /**
     * 消息类型
     */
    private String event;


    /**
     * 渠道ID 用来识别是那个用户
     */
    private String channelId;


    /**
     * 消息内容
     */
    private String message;

}
