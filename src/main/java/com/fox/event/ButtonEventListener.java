package com.fox.event;

/**
 * <p>
 * 按钮事件监听
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/8 package: com.fox.event
 */
public interface ButtonEventListener extends EventListener {


    /**
     * 点击
     */
    void click();


    /**
     * 改变
     */
    void change();


    /**
     * 按钮创建
     */
    void created();


    /**
     * 被销毁
     */
    void destroyed();


}

