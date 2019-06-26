package cn.rock.mybatis01;

import java.lang.reflect.Proxy;
import java.sql.SQLException;

/**
 * @author wjl48511
 * @create 2019/6/25-11:21
 **/
public class DefaultSqlSession implements SqlSession {

    private Executor executor = new BaseExecutor();

    @Override
    public <T> T selectOne(String sql) throws SQLException {
       return executor.query(sql);
    }

    @Override
    public <T> T getMapper(Class<T> interfaces) {
        return (T)Proxy.newProxyInstance(interfaces.getClassLoader(), new Class[]{interfaces}, new MapperProxy(this));
    }
}
