package com.fox.demo.java.button;

import com.fox.annotation.View;
import com.fox.event.ButtonEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 页面-我的快乐按钮
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/13 package: com.fox.demo.java.button
 */
@Slf4j
@View("my_happy")
public class MyHappyButton extends ButtonEvent {


    @Override
    public void click() {

        // 按钮点击时执行
        log.info("按钮执行了");

    }

}
