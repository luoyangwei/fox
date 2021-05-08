package com.fox.annotation;

import java.lang.annotation.*;

/**
 * <p>
 * 设置视图
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/8 package: com.fox.annotation
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface View {


    /**
     * 设置视图的ID跟前端绑定
     *
     * @return 设置的ID
     */
    String id() default "";


    /**
     * 同id方法
     *
     * @return 设置的ID
     */
    String value() default "";

}
