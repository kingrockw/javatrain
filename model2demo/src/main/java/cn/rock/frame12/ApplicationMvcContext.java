package cn.rock.frame12;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

//https://blog.csdn.net/qq_33243355/article/details/80557905

public class ApplicationMvcContext {

    private static Map<String, MvcDefine> mvcContext = new HashMap<>();

    public static MvcDefine getMvcDefine(String requestURI) {
        return mvcContext.get(requestURI);
    }

    public static void init(String controllerPath) throws IOException {

        // 是否循环迭代
        boolean recursive = true;
        Set<Class<?>> classes = new LinkedHashSet<>();
        String packageName = controllerPath;
        String packageDirName = packageName.replace('.', '/');

        Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);

        while (dirs.hasMoreElements()) {
            // 获取下一个元素
            URL url = dirs.nextElement();
            // 得到协议的名称
            String protocol = url.getProtocol();
            if ("file".equals(protocol)) {
                System.err.println("file类型的扫描");
                // 获取包的物理路径
                String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                // 以文件的方式扫描整个包下的文件 并添加到集合中
                findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
            }
        }
        parseMvcDefine(classes,mvcContext);
    }

    private static void parseMvcDefine(Set<Class<?>> classes, Map<String, MvcDefine> mvcContext) {
        for (Class<?> item : classes){
            Object o = null;
            try {
                o = item.newInstance();
            } catch (InstantiationException e) {

            } catch (IllegalAccessException e) {

            }
            if (o != null){
                Method[] methods = item.getDeclaredMethods();
                for(Method method : methods){
                    RequestMapping annotation = method.getAnnotation(RequestMapping.class);
                    if (null != annotation){
                        String path = annotation.value();
                        MvcDefine mvcDefine = new MvcDefine(path,method,o,item);
                        mvcContext.put(path,mvcDefine);
                    }
                }
            }

        }
    }

    /**
     * 以文件的形式来获取包下的所有Class
     *
     * @param packageName 包的名字
     * @param packagePath 包的路径
     * @param recursive
     * @param classes 文件列表
     */
    public static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive,
                                                        Set<Class<?>> classes) {
        // 获取此包的目录 建立一个File
        File dir = new File(packagePath);
        // 如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        // 如果存在 就获取包下的所有文件 包括目录
        File[] dirfiles = dir.listFiles(new FileFilter() {
            // 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
            @Override
            public boolean accept(File file) {
                return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
            }
        });
        // 循环所有文件
        for (File file : dirfiles) {
            // 如果是目录 则继续扫描
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive,
                        classes);
            } else {
                // 如果是java类文件 去掉后面的.class 只留下类名
                String className = file.getName().substring(0, file.getName().length() - 6);
                try {
                    //classes.add(Class.forName(packageName + '.' + className));
                    //经过回复同学的提醒，这里用forName有一些不好，会触发static方法，没有使用classLoader的load干净
                    Class<?> clazz = Class.forName(packageName + '.' + className);
//                    Class<?> clazz = Thread.currentThread().getContextClassLoader()
//                            .loadClass(packageName + '.' + className);

                    Controller annotation = clazz.getAnnotation(Controller.class);
                    if (null != annotation){
                        classes.add(clazz);
                    }
                } catch (ClassNotFoundException e) {

                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init("cn.rock.business.controller");
    }

    public static class MvcDefine {
        private String path;

        private Method method;

        private Object target;

        private Class<?> clazz;



        public MvcDefine(String path, Method method, Object target, Class<?> item) {
            this.path = path;
            this.method = method;
            this.target = target;
            this.clazz = item;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public Method getMethod() {
            return method;
        }

        public void setMethod(Method method) {
            this.method = method;
        }

        public Object getTarget() {
            return target;
        }

        public void setTarget(Object target) {
            this.target = target;
        }

        public Class<?> getClazz() {
            return clazz;
        }

        public void setClazz(Class<?> clazz) {
            this.clazz = clazz;
        }
    }
}
