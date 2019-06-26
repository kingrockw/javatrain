import cn.rock.entity.Blog;
import cn.rock.mybatis.BlogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author wjl48511
 * @create 2019/6/24-20:46
 **/
public class MybatisTest {
    //加载配置文件
    String resource = "mybatisConfig.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    public MybatisTest() throws IOException {}

    @Test
    public void search(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        Blog blog = sqlSession.selectOne("cn.rock.mybatis.BlogMapper.findBlogById",6);

        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = mapper.findBlogById(6);
        System.out.println(blog.getBlogId());
        System.out.println(blog.getContent());
    }

    @Test
    public void insert() {

        //根据SqlSessionFactory创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //添加用户
        //输入参数
        Blog blog = new Blog();
        blog.setContent("我的mybatis blog");
        blog.setTitle("mybatis");
        blog.setUserId(12);
        sqlSession.insert("cn.rock.mybatis.BlogMapper.insertBlog", blog);
        //提交 事务
        sqlSession.commit();
        //获取新添加用户的id
        long id = blog.getBlogId();
        System.out.println("blog" + id);
        //关闭sqlSession
        sqlSession.close();
    }



}
