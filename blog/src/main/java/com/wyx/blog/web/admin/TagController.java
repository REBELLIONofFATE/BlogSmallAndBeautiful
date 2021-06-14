package com.wyx.blog.web.admin;

import com.wyx.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Create by WYX on 2021/6/14 8:22
 **/
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

//    @GetMapping("/tags")
//    public String tags(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)
//                                   Pageable pageable, Model model){
//        model.addAttribute("page",tagService.listTag(pageable));
//        return "admin/tags";
//    }

    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 3,sort = {"id"},direction = Sort.Direction.DESC)
                               Pageable pageable, Model model) {
        model.addAttribute("page",tagService.listTag(pageable));
        System.out.println(model.getAttribute("page"));
        return "admin/tags";
    }
}
