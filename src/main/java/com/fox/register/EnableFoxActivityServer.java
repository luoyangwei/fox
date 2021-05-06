package com.fox.register;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>
 * 启用Fox
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/6 package: com.fox.register
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({EnableFoxActivityImportRegister.class})
public @interface EnableFoxActivityServer {
}
