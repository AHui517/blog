package com.kong.blog.controller;

import com.kong.blog.domain.*;
import com.kong.blog.service.BlogService;
import com.kong.blog.service.TagService;
import com.kong.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 5,sort = {"id"})Pageable pageable, BlogQuery blog, Model model){
//        获取所有的类型
        model.addAttribute("blogs",blogService.listBlog(pageable,blog));
        model.addAttribute("types",typeService.findAll());
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 5,sort = {"id"})Pageable pageable, BlogQuery blog, Model model){
        System.out.println(blog);
        model.addAttribute("blogs",blogService.listBlog(pageable,blog));
        return "admin/blogs::blogList";
    }

    @GetMapping("/blogs/input")
    public String input(Model model){
//        新增页面 需要提供所有的标签和分类
        List<Type> listType = typeService.findAll();
        List<Tag> listTag = tagService.findAll();
        model.addAttribute("tags",listTag);
        model.addAttribute("types",listType);
        return "admin/blogs-input";
    }

    @PostMapping("/blogs")
    public String postBlogs(Blog blog, HttpSession session){
            blog.setType(typeService.getType(blog.getType().getId()));
            blog.setUser((User)session.getAttribute("user"));
            blog.setTags(tagService.listTag(blog.getTagIds()));
            blogService.saveBlog(blog);
            return "redirect:/admin/blogs";
    }

    @GetMapping("/deleteBlog/{id}")
    public String deleteBlog(@PathVariable Long id){
        blogService.deleteBlog(id);
        return "redirect:/admin/blogs";
    }

    @GetMapping("/editBlog/{id}")
    public String eidtBolg(@PathVariable Long id,Model model){
        Blog blog = blogService.getBlog(id);
        blog.tagsToString();
        model.addAttribute("blog",blog);
        List<Type> listType = typeService.findAll();
        List<Tag> listTag = tagService.findAll();
        model.addAttribute("tags",listTag);
        model.addAttribute("types",listType);
        return "admin/blogs-edit";
    }
}
