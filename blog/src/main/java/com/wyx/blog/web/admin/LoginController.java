package com.wyx.blog.web.admin;

import com.wyx.blog.po.User;
import com.wyx.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * Create by WYX on 2021/6/3 22:59
 **/

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    @GetMapping("/login")
    public String loginGet(HttpSession session){
        if (session.getAttribute("user")!=null){
            return "admin/index";
        }else{
            return "redirect:/admin";
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        HttpSession session, RedirectAttributes attributes){
        User user = userService.checkUser(username,password);
            if(user != null){
                user.setPassword(null);//将密码置为空,为了安全
            session.setAttribute("user",user);//将User对象存入session
            return "admin/index";
        }else {
            attributes.addFlashAttribute("message","用户名密码错误");//登录验证不通过,设置提示信息
            return "redirect:/admin";//重定向到登录界面,并带有重定向设置的提示信息
        }
    }

    @GetMapping("/logout")  //登出
    public String logout(HttpSession session){
        session.removeAttribute("user");//登录成功的过程中用户信息被存在session中,移出session中的User
        return "redirect:/admin";   //重定向到登录到登录界面
    }

}


















