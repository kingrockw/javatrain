package cn.rock.mybatis01;

import cn.rock.entity.Blog;

/**
 * @author wjl48511
 * @create 2019/6/25-12:12
 **/
public class BootMain {
    public static void main(String[] args) {

        SqlSession sqlSession = new DefaultSqlSession();

        BlogMapper01 mapper = sqlSession.getMapper(BlogMapper01.class);

        Blog blog = mapper.findBlogById(6);

        System.out.println(blog);
    }
}
