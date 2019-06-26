package cn.rock.mybatis01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author wjl48511
 * @create 2019/6/25-11:24
 **/
public class MapperProxy implements InvocationHandler{

    private SqlSession sqlSession;

    public MapperProxy(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String name = method.getDeclaringClass().getName();
        if (BlogMapperXml.nameSpace.equals(name)){
            //获取SQL
            String originSql = BlogMapperXml.getMethodSql(method.getName());
            //解析SQL参数
            String formatSql = String.format(originSql, String.valueOf(args[0]));
            return sqlSession.selectOne(formatSql);
        }

        return null;
    }
}
