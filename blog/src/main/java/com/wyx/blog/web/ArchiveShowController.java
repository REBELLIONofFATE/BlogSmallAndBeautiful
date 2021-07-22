package com.wyx.blog.web;

import com.wyx.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Create by WYX on 2021/7/22 23:18
 **/
@Controller
public class ArchiveShowController {

    @Autowired
    BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model){

        model.addAttribute("archiveMap",blogService.archiveBlog());
        model.addAttribute("blogCount",blogService.countBlog());
        return "archives";
    }



}
