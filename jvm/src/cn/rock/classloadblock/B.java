package cn.rock.classloadblock;

public class B {
    static {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Class.forName("cn.rock.classloadblock.A");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("B init OK");
    }
}
