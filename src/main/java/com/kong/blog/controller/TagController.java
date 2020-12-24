package com.kong.blog.controller;

import com.kong.blog.domain.Tag;
import com.kong.blog.domain.Type;
import com.kong.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/admin")
@Controller
public class TagController {

    @Autowired
    private TagService tagService;

//    展示界面
    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 5,sort = {"id"}) Pageable pageable,Model model){
        Page<Tag> tags = tagService.getAll(pageable);
        System.out.println(tags);
        model.addAttribute("tags",tags);
        return "admin/tags";
    }

    @PostMapping("/tags")
    public String addtag(@PageableDefault(size = 5,sort = {"id"}) Pageable pageable,Model model,Tag tag){
        if (tagService.findByName(tag.getName())==null){
            tagService.saveTag(tag);
            return "redirect:/admin/tags";
        }
        return "admin/tags-input";
    }

//    删除
    @GetMapping("/tags/delete/{id}")
    public String deleteTatg(@PathVariable Long id){
        tagService.deleteTag(id);
        return "redirect:/admin/tags";
    }

//    新增
    @GetMapping("/tags/input")
    public String addTag(){
        return "admin/tags-input";
    }

//    编辑
    @GetMapping("/tags/edit/{id}")
    public String editTag(@PathVariable Long id,Model model){
        model.addAttribute("id",id);
        return "admin/tags-edit";
    }
}
