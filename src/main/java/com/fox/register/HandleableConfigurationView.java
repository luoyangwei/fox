package com.fox.register;

import com.fox.annotation.View;
import com.fox.utils.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.util.Objects;

/**
 * <p>
 * 可配置的视图
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/13 package: com.fox.register
 */
public class HandleableConfigurationView {


    /**
     * 类上面所有的直接
     */
    private Annotation[] annotations;


    /**
     * 配置的视图
     */
    private String viewId;


    /**
     * 实际实现的activity对象
     */
    private Class<?> clazz;


    public HandleableConfigurationView(Class<?> clazz) {
        setClazz(clazz);
    }


    public void setClazz(Class<?> clazz) {
        Objects.requireNonNull(clazz);
        this.clazz = clazz;

        annotations = clazz.getAnnotations();
        Objects.requireNonNull(annotations);

        View view = clazz.getAnnotation(View.class);
        if (view != null) {
            viewId = AnnotationUtils.getViewValue(view);
        }
    }

    public Class<?> getClazz() {
        return clazz;
    }

}
