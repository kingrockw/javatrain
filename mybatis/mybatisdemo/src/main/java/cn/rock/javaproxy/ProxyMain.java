package cn.rock.javaproxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wjl48511
 * @create 2019/6/25-9:58
 **/
public class ProxyMain {
    public static void main(String[] args) {

        KeeperProxy keeperProxy = new KeeperProxy(new Cat());
        Animal animal = (Animal) Proxy.newProxyInstance(Animal.class.getClassLoader(), new Class[]{Animal.class},
                keeperProxy);
        animal.eat("鱼");


        Animal long_animal = (Animal) Proxy.newProxyInstance(Animal.class.getClassLoader(), new Class[]{Animal
                .class},new LongProxy());

        long_animal.eat("闪电");
    }
}
