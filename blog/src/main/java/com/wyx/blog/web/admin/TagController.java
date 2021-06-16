package com.wyx.blog.web.admin;

import com.wyx.blog.po.Tag;
import com.wyx.blog.po.Type;
import com.wyx.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Create by WYX on 2021/6/14 8:22
 **/
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)
                                   Pageable pageable, Model model){
        model.addAttribute("page",tagService.listTag(pageable));
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String TagsInput(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }


    @PostMapping("/tags")
    public String post(@Valid Tag tag,BindingResult result,RedirectAttributes attributes){
        Tag t =tagService.getTagByName(tag.getName());
        if(t != null){
            attributes.addFlashAttribute("message","标签添加失败,请勿重复添加");
            return "redirect:/admin/tags";
        }

        if (result.hasErrors()){
            return "admin/tags-input";
        }

        Tag tag1 =tagService.saveTag(tag);
        if (tag1 !=null){
            attributes.addFlashAttribute("message","标签添加成功");
        }else {
            attributes.addFlashAttribute("message","标签添加失败");
        }
        return "redirect:/admin/tags";
    }

//    将tag对象传递到编辑页面
    @GetMapping("/tags/{id}/input")
    public String edit(@PathVariable Long id,Model model){
        Tag tag = tagService.getTag(id);
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tags-input";
    }

    @PostMapping("/tags/{id}")
    public String edit(@Valid Tag tag,BindingResult result,RedirectAttributes attributes){
        if (result.hasErrors()){
            return "admin/tags";
        }
        Tag t =tagService.getTagByName(tag.getName());
        if(t != null){
            attributes.addFlashAttribute("message","标签添加失败,请勿重复添加");
            return "redirect:/admin/tags";
        }
        tagService.saveTag(tag);
        attributes.addFlashAttribute("message","标签修改成功");
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        String tagName;
        tagName = (tagService.getTag(id)).getName();
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","标签"+tagName+"成功删除");
        return "redirect:/admin/tags";
    }

}
