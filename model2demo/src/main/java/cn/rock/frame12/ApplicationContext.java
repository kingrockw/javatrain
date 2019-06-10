package cn.rock.frame12;


import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private static Map<Class,Object> context = new HashMap<>();

    public static <T> T getBean(Class<T> userServiceClass) {
        return  (T)context.get(userServiceClass);
    }
}
