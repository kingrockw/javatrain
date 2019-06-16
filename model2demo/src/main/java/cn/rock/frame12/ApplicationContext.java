package cn.rock.frame12;


import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private static Map<Class,Object> context = new HashMap<>();

    public static <T> T getBean(Class<T> userServiceClass) {
        return  (T)context.get(userServiceClass);
    }

    public static void init(String path){
        InputStream resourceAsStream = ApplicationContext.class.getClassLoader().getResourceAsStream(path);
        try {
            BufferedReader  reader = new BufferedReader(new InputStreamReader(resourceAsStream));
            String confLine = null;
            while ((confLine = reader.readLine()) !=null){
                Class<?> aClass = Thread.currentThread().getContextClassLoader().loadClass(confLine);
                context.put(aClass,aClass.newInstance());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}
