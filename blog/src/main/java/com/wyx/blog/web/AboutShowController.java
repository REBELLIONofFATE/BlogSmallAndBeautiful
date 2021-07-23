package com.wyx.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Create by WYX on 2021/7/23 7:56
 **/
@Controller
public class AboutShowController {


    @GetMapping("/about")
    public String about() {
        return "about";
    }






}
