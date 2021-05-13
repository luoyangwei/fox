package com.fox.utils;

import com.fox.annotation.View;

import java.lang.annotation.Annotation;
import java.util.Objects;

/**
 * <p>
 * 注解Utils
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/13 package: com.fox.utils
 */
//@View("1")
public class AnnotationUtils {


    /**
     * 视图注解
     */
    private static final Class<View> viewAnnotationClass = View.class;


    public static boolean hasViewAnnotated(Class<?> clazz, Class<?> annotationClass) {
        Objects.requireNonNull(annotationClass);

        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {

            return annotation instanceof View;
        }
        return false;
    }


    public static String getViewValue(View annotationView) {
        return annotationView.id().isEmpty() ? annotationView.value() : annotationView.id();
    }

    public static void main(String[] args) {
        System.out.println(hasViewAnnotated(AnnotationUtils.class, View.class));
    }

}
