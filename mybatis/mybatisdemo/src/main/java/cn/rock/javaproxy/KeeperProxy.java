package cn.rock.javaproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author wjl48511
 * @create 2019/6/25-9:59
 **/
public class KeeperProxy implements InvocationHandler {

    private Object target;

    public KeeperProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("eat")){
             System.out.println("小猫饿了，饲养员来喂食");
        }
        Object invoke = method.invoke(target,args);
        return invoke;
    }
}
