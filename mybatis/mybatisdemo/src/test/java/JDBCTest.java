import cn.rock.entity.Blog;
import cn.rock.jdbc.BlogDaoImpl;
import org.junit.Test;

/**
 * @author wjl48511
 * @create 2019/6/24-16:47
 **/
public class JDBCTest {

    @Test
    public void saveBlog() {
        Blog blog = new Blog();
        blog.setContent("我的第一篇blog");
        blog.setTitle("jdbc");
        blog.setUserId(12);
        BlogDaoImpl blogDao = new BlogDaoImpl();
        blogDao.addBlog(blog);
    }

    @Test
    public void getBlog() {
        BlogDaoImpl blogDao = new BlogDaoImpl();
        Blog blog = blogDao.getBlogById(1);
        System.out.println(blog.getContent());
    }

}
