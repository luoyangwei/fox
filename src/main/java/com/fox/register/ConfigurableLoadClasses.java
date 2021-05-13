package com.fox.register;

import com.fox.activity.Activity;
import com.fox.annotation.View;
import com.fox.event.ButtonEvent;
import com.fox.event.EventListener;
import com.fox.utils.AnnotationUtils;
import com.fox.utils.ClassLoaderUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 加载class
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/12 package: com.fox.register
 */
@Slf4j
public class ConfigurableLoadClasses {


    /**
     * 加载class的路径
     */
    private String basePackage;


    /**
     * Class集合
     */
    private static final Map<String, Class<?>> enduranceClasses = new ConcurrentHashMap<>();


    /**
     * Activity
     */
    private static final Map<String, HandleableConfigurationView> activityClasses = new ConcurrentHashMap<>();

    /**
     * Button
     */
    private static final Map<String, HandleableConfigurationView> buttonClasses = new ConcurrentHashMap<>();


    public ConfigurableLoadClasses() {
    }


    public ConfigurableLoadClasses(String basePackage) {
        this.basePackage = basePackage;
        scanFiles(basePackage);
    }


    public void scanFiles(String basePackage) {
        try {

            Class<?>[] classes = ClassLoaderUtils.getClasses(basePackage);
            for (Class<?> loadedClass : classes) {
                enduranceClasses.put(loadedClass.getSimpleName(), loadedClass);

                log.info("Load class: {}", loadedClass);
                if (!loadedClass.isInterface() && !loadedClass.isAnnotation() && !Modifier.isAbstract(loadedClass.getModifiers())) {
                    Object theObject = loadedClass.newInstance();

                    // Activity
                    if (theObject instanceof Activity) {
                        packageViewAnnotateClasses(activityClasses, new HandleableActivity(loadedClass));
                    }

                    // Event
                    if (theObject instanceof EventListener) {

                        // Button
                        if (theObject instanceof ButtonEvent) {
                            packageViewAnnotateClasses(buttonClasses, new HandleableButton(loadedClass));
                        }
                    }

                }

            }

            log.info("Class activity size: {}", activityClasses.size());
            log.info("Class button size: {}", buttonClasses.size());

        } catch (ClassNotFoundException classNotFoundException) {

            log.error("classNotFoundException", classNotFoundException);
        } catch (IOException ioe) {

            log.error("ioe", ioe);
        } catch (IllegalAccessException illegalAccessException) {

            log.error("illegalAccessException", illegalAccessException);
        } catch (InstantiationException instantiationException) {

            log.error("instantiationException", instantiationException);
        }
    }


    public Map<String, HandleableConfigurationView> getActivityClasses() {
        return activityClasses;
    }


    public Map<String, HandleableConfigurationView> getButtonClasses() {
        return buttonClasses;
    }


    /**
     * 把视图配置更新到集合里面
     *
     * @param classesMap                  视图结合
     * @param handleableConfigurationView 视图
     */
    private void packageViewAnnotateClasses(Map<String, HandleableConfigurationView> classesMap, HandleableConfigurationView handleableConfigurationView) {
        if (AnnotationUtils.hasViewAnnotated(handleableConfigurationView.getClazz(), View.class)) {

            View view = handleableConfigurationView.getClazz().getAnnotation(View.class);
            String viewId = AnnotationUtils.getViewValue(view);
            classesMap.put(viewId, handleableConfigurationView);
        }
    }


}
