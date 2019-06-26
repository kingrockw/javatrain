package cn.rock.mybatis01;

import cn.rock.entity.Blog;

/**
 * @author wjl48511
 * @create 2019/6/24-20:32
 **/
public interface BlogMapper01 {

    Blog findBlogById(long blogId);

}
