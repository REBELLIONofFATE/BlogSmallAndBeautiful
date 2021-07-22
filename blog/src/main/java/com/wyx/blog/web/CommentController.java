package com.wyx.blog.web;

import com.wyx.blog.po.Comment;
import com.wyx.blog.po.User;
import com.wyx.blog.service.BlogService;
import com.wyx.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * Create by WYX on 2021/7/19 12:54
 **/

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model) {
        model.addAttribute("comments", commentService.listCommentByBlogId(blogId));
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));
        User user =  (User) session.getAttribute("user");
        if (user != null ){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
            comment.setNickname(user.getNickname());
        }else{
            comment.setAvatar(avatar);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/" + comment.getBlog().getId();
    }
}


























