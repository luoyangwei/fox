package com.fox.event;

/**
 * <p>
 * 元素变更事件注册
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/8 package: com.fox.event
 */
public interface EventListener {


    /**
     * 按钮创建
     */
    void created();


    /**
     * 被销毁
     */
    void destroyed();

}
