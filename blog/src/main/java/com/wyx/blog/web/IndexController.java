package com.wyx.blog.web;

import com.wyx.blog.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){

        return "index";
    }

    @GetMapping("/blog")
    public String blog(){
        return "blog";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/tags")
    public String tags(){
        return "tags";
    }

    @GetMapping("/types")
    public String types(){
        return "types";
    }

    @GetMapping("/archives")
    public String archives(){
        return "archives";
    }


}













