package cn.rock.transactiondemo.transaction;

/**
 *
 * @author wjl48511
 * @create 2019/7/1-17:52
 **/
public class TransactionResourceManager {

    private static ThreadLocal resource = new ThreadLocal();

    public static Object getResource() {
        return resource.get();
    }

    public static void bindResource(Object res) {
        resource.set(res);
    }

    public static Object unbindResource(){
        Object res = getResource();
        resource.set(null);
        return  res;
    }
}
