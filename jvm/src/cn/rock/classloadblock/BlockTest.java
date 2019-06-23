package cn.rock.classloadblock;

public class BlockTest extends Thread {
    private char flag;

    public BlockTest(char flag) {
        this.flag = flag;
        this.setName("Thread"+flag);
    }

    @Override
    public void run() {

        try {
            Class.forName("cn.rock.classloadblock." +flag);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(getName()+" over");
    }

    public static void main(String[] args) {
        BlockTest blockTesta = new BlockTest('A');
        BlockTest blockTestb = new BlockTest('B');
        blockTesta.start();
        blockTestb.start();
    }
}
