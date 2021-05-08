package com.fox.event;

import java.io.Serializable;

/**
 * <p>
 * ws连接创建事件
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/8 package: com.fox.event
 */
public class ConnectionOnCreatedEvent implements Serializable {

    private static final long serialVersionUID = 9048068124870800999L;


    /**
     * 连接创建事件
     */
    private static final String EVENT_TYPE = "on_created";




}
