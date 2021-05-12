package com.fox.register;

import com.fox.activity.Activity;
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
     *
     */
    private String basePackage;


    /**
     * Class集合
     */
    private static final Map<String, Class<?>> enduranceClasses = new ConcurrentHashMap<>();


    private static final Map<String, Class<?>> activityClasses = new ConcurrentHashMap<>();


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

                        log.info("theObject: {}", theObject);
                        activityClasses.put(loadedClass.getSimpleName(), loadedClass);
                    }
                }

            }

            log.info("Class activity size: {}", activityClasses.size());

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

}
