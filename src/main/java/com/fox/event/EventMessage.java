package com.fox.event;

import java.io.Serializable;

/**
 * <p>
 * 事件消息
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/8 package: com.fox.event
 */
public class EventMessage<T> implements Serializable {

    private static final long serialVersionUID = -1942016401642825714L;


    /**
     * 事件
     */
    private String event;


    /**
     * 消息内容
     */
    private T message;

}
