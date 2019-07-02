package cn.rock.transactiondemo.entity;

import java.util.Date;

/**
 * @author wjl48511
 * @create 2019/7/1-16:42
 **/
public class Blog {

    private Long blogId;

    private Long userId;

    private String title;

    private String content;

    private Date addDate;

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }
}
