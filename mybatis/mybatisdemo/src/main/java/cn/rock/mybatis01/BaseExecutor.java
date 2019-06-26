package cn.rock.mybatis01;

import cn.rock.entity.Blog;
import cn.rock.jdbc.BeanUtil;

import java.sql.*;

/**
 * @author wjl48511
 * @create 2019/6/25-11:22
 **/
public class BaseExecutor implements Executor {

    private static String url;
    private static String user;
    private static String password;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost:3306/mybatisdemo?characterEncoding=utf-8&serverTimezone=GMT%2B8";
            user = "root";
            password = "king";
        } catch (ClassNotFoundException e) {
        }
    }

    @Override
    public <T> T query(String sql) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Blog blog = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                blog = new Blog();
                blog.setBlogId(resultSet.getLong(1));
                blog.setUserId(resultSet.getInt(2));
                blog.setTitle(resultSet.getString(3));
                blog.setContent(resultSet.getString(4));
                blog.setAddDate(resultSet.getDate(5));
                return (T) blog;
            }
            blog = BeanUtil.toBlog(resultSet);
        } catch (Exception e) {

        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return (T) blog;

    }
}
