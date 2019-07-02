package cn.rock.transactiondemo.dao;

import cn.rock.transactiondemo.entity.Blog;

/**
 * @author wjl48511
 * @create 2019/7/1-16:41
 **/
public interface BlogMapper {

    Integer insertBlog(Blog blog);

    Blog findBlogById();

}
