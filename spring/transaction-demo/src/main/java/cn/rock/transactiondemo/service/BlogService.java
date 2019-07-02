package cn.rock.transactiondemo.service;

import cn.rock.transactiondemo.entity.Blog;

/**
 * @author wjl48511
 * @create 2019/7/1-17:35
 **/
public interface BlogService {

    Integer save(Blog blog);

    Integer stateSave(Blog blog) throws ClassNotFoundException ;

    Integer templateSave(Blog blog);
}
