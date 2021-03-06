package com.wyx.blog.web;

import com.wyx.blog.po.Tag;
import com.wyx.blog.service.BlogService;
import com.wyx.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Create by WYX on 2021/7/22 15:04
 * admin 下也有TagController,命名不能相同
 **/

@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(@PageableDefault(size=8,sort={"updateTime"},direction = Sort.Direction.DESC)Pageable pageable,
                        @PathVariable Long id, Model model){
        List<Tag> tags =tagService.listTagTop(100000);
        if (id == -1){
            id = tags.get(0).getId();
        }

        model.addAttribute("tags",tags);
        model.addAttribute("page",blogService.listBlog(id, pageable));
        model.addAttribute("activeTagId",id);
        return "tags";
    }








}





















