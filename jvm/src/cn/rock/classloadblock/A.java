package cn.rock.classloadblock;

public class A {
    static {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Class.forName("cn.rock.classloadblock.B");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("A init OK");
    }
}
