package com.fox.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * <p>
 * Class 加载工具包
 * </p>
 *
 * @author 番茄ICE
 * @since 2021/5/12 package: com.fox.utils
 */
public class ClassLoaderUtils {


    /**
     * 类加载器
     */
    private final static ClassLoader classLoader;


    static {

        classLoader = Thread.currentThread().getContextClassLoader();
    }


    /**
     * 从上下文类加载器中扫描给定的包及其子包下面的所有类
     *
     * @param packageName 包名
     *
     * @return class array
     *
     * @throws ClassNotFoundException 没有找到class异常
     * @throws IOException            io 异常
     */
    public static Class<?>[] getClasses(String packageName) throws ClassNotFoundException, IOException {
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class<?>> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[]{});
    }


    /**
     * 用递归的方法查找给定的目录及其子目录中的所有的类
     *
     * @param directory   给定的目录
     * @param packageName 包名
     *
     * @return 包名 + 类名
     *
     * @throws ClassNotFoundException 没有找到class异常
     */
    public static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    assert !file.getName().contains(".");
                    classes.addAll(findClasses(file, packageName + "." + file.getName()));
                } else if (file.getName().endsWith(".class")) {
                    classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
                }
            }
        }
        return classes;
    }

}
