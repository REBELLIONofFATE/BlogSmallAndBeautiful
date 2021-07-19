package com.wyx.blog.service;

import com.wyx.blog.po.Comment;

import java.util.List;

/**
 * Create by WYX on 2021/7/19 13:15
 **/
public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
 }
