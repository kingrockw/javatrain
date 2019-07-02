package cn.rock.transactiondemo;

import cn.rock.transactiondemo.dao.BlogMapper;
import cn.rock.transactiondemo.entity.Blog;
import cn.rock.transactiondemo.service.BlogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionDemoApplicationTests {

	@Autowired
	BlogService  blogService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void  testBlog(){
		Blog blog = new Blog();
		blog.setTitle("71");
		blog.setUserId(12L);
		blog.setContent("1111111111");
		blogService.save(blog);
	}

	@Test
	public void  testStateBlog() throws ClassNotFoundException {
		Blog blog = new Blog();
		blog.setTitle("72");
		blog.setUserId(12L);
		blog.setContent("22222");
		blogService.stateSave(blog);
	}
}
