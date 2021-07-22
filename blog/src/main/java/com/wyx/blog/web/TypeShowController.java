package com.wyx.blog.web;

import com.wyx.blog.po.Type;
import com.wyx.blog.service.BlogService;
import com.wyx.blog.service.TypeService;
import com.wyx.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.ClientInfoStatus;
import java.util.List;

/**
 * Create by WYX on 2021/7/22 15:04
 * admin 下也有TypeController,命名不能相同
 **/

@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size=8,sort={"updateTime"},direction = Sort.Direction.DESC)Pageable pageable,
                        @PathVariable Long id, Model model){
        List<Type> types =typeService.listTypeTop(100000);
        if (id == -1){
            id = types.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("types",types);
        model.addAttribute("page",blogService.listBlog(pageable,blogQuery));
        model.addAttribute("activeTypeId",id);
        return "types";
    }








}





















