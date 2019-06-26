package cn.rock.javaproxy;

/**
 * @author wjl48511
 * @create 2019/6/25-10:12
 **/
public class Cat implements Animal {

    @Override
    public void eat(String food) {
         System.out.println("小猫爱吃："+food);
    }
}
