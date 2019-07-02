package cn.rock.transactiondemo.service.impl;

import cn.rock.transactiondemo.dao.BlogMapper;
import cn.rock.transactiondemo.entity.Blog;
import cn.rock.transactiondemo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

/**
 * @author wjl48511
 * @create 2019/7/1-17:36
 **/
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    DataSource dataSource;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    TransactionTemplate transactionTemplate;
    @Override
    public Integer save(Blog blog) {

        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
        Integer integer = 0;
        try{
            integer = blogMapper.insertBlog(blog);
            if (true) {
                throw  new NullPointerException("ddddd");
            }
        }catch (Exception e){
            platformTransactionManager.rollback(transactionStatus);
        }
        platformTransactionManager.commit(transactionStatus);

        return integer;
    }

    /**
     * Transactional 默认只回滚runtime异常 , 如果非runtime异常也需要回滚，需要rollbackFor 指定 方法中声明的异常
     * @param blog
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    @Transactional(rollbackFor = {ClassNotFoundException.class})
    public Integer stateSave(Blog blog) throws ClassNotFoundException {
        Integer integer = blogMapper.insertBlog(blog);
        if (true) {
                throw  new ClassNotFoundException("CCCCC");
//            try {
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
        }
        return integer;
    }

    @Override
    public Integer templateSave(Blog blog) {
        transactionTemplate.execute(new TransactionCallback<Integer>() {
            @Nullable
            @Override
            public Integer doInTransaction(TransactionStatus status) {

                return blogMapper.insertBlog(blog);
            }
        });
        return null;
    }
}
