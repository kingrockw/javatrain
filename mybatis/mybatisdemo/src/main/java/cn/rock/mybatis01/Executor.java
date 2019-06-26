package cn.rock.mybatis01;

import java.sql.SQLException;

/**
 * @author wjl48511
 * @create 2019/6/25-11:12
 **/
public interface Executor {

    <T> T query(String sql) throws SQLException;
}
