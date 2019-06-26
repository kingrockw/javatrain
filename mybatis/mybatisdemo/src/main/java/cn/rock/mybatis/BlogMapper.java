package cn.rock.mybatis;

import cn.rock.entity.Blog;
import org.apache.ibatis.annotations.Param;

/**
 * @author wjl48511
 * @create 2019/6/24-20:32
 **/
public interface BlogMapper {

    Blog findBlogById(@Param("blogId") long blogId);

    Integer insertBlog(Blog blog);
}
