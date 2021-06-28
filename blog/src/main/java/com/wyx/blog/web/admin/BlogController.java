package com.wyx.blog.web.admin;

import com.wyx.blog.po.Blog;
import com.wyx.blog.po.User;
import com.wyx.blog.service.BlogService;
import com.wyx.blog.service.TagService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * Create by WYX on 2021/6/7 22:19
 **/
@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT = "/admin/blogs-input";
    private static final String LIST = "/admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 3,sort = {"updateTime"},direction = Sort.Direction.DESC)
                                    Pageable pageable, BlogQuery blog, Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return LIST;
    }

    //处理ajax请求
    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 3,sort = {"updateTime"},direction = Sort.Direction.DESC)
                                     Pageable pageable, BlogQuery blog, Model model){
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        //返回的是一个fragment,是一段html代码,用于替换原页面的部分
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/input")
    public String input( Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        model.addAttribute("blog",new Blog());
        return INPUT;
    }

    private void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
    }

    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        setTypeAndTag(model);
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog);
        return INPUT;
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return REDIRECT_LIST;
    }



    @PostMapping("/blogs")
public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
    blog.setUser((User) session.getAttribute("user"));
    //根据页面传回的参数自动生成一个blog,blog的type属性主动生成,但里面只有响应的id,然后通过id查询相应的type,再设置给blog
    blog.setType(typeService.getType(blog.getType().getId()));
    //页面的tagids会成为字符串封装再tagids属性而不是tag属性中
    blog.setTags(tagService.listTag(blog.getTagIds()));
    Blog b;
    if (blog.getId() == null) {
        b =  blogService.saveBlog(blog);
    } else {
        b = blogService.updateBlog(blog.getId(), blog);
    }

    if (b == null ) {
        attributes.addFlashAttribute("message", "操作失败");
    } else {
        attributes.addFlashAttribute("message", "操作成功");
    }
    return REDIRECT_LIST;
}

}













