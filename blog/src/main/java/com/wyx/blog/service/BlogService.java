package com.wyx.blog.service;

import com.wyx.blog.po.Blog;
import com.wyx.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Create by WYX on 2021/6/16 14:55
 **/
public interface BlogService {
    Blog getBlog(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Blog saveBlog(Blog blog);

    void deleteBlog(Long id);

    Blog updateBlog(Long id ,Blog blog);
}
