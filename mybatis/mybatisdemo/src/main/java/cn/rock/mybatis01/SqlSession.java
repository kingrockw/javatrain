package cn.rock.mybatis01;

import java.sql.SQLException;

/**
 * @author wjl48511
 * @create 2019/6/25-11:19
 **/
public interface SqlSession {

    <T> T selectOne(String sql) throws SQLException;

    <T> T getMapper(Class<T> clazz);
}
