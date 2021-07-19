package com.wyx.blog.dao;

import com.wyx.blog.po.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Create by WYX on 2021/7/19 16:57
 **/
public interface CommentRepository extends JpaRepository<Comment,Long> {


    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);

}



















