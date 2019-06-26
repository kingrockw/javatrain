package cn.rock.javaproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author wjl48511
 * @create 2019/6/25-10:38
 **/
public class LongProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("eat")) {
            System.out.println("龙饿了！！！");
        }
        System.out.println("龙吃：" + args[0]);
        return null;
    }

    @Override
    public String toString() {
        return "LongProxy{}";
    }
}
