package com.wyx.blog.service;

import com.wyx.blog.po.Blog;
import com.wyx.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * Create by WYX on 2021/6/16 14:55
 **/
public interface BlogService {
    Blog getBlog(Long id);

    Blog getAndConvert(Long id);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(Long id, Pageable pageable);

    Page<Blog> listBlog(String query,Pageable pageable);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Map<String,List<Blog>> archiveBlog();

    Long countBlog();

    List<Blog> listRecommendBlogTop(Integer size );

    Blog saveBlog(Blog blog);

    void deleteBlog(Long id);

    Blog updateBlog(Long id ,Blog blog);
}
