package com.kong.blog.controller;

import com.kong.blog.domain.Blog;
import com.kong.blog.domain.BlogQuery;
import com.kong.blog.domain.Tag;
import com.kong.blog.service.BlogService;
import com.kong.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tag/{id}")
    public String showTags(@PathVariable Long id, @PageableDefault(size = 100,sort = {"updateTime"}) Pageable pageable, Model model){
        List<Tag> tags = tagService.findAll();
        if (id==-1){
            id=tags.get(0).getId();
        }
        Page<Blog> blogs = blogService.findByTagId(id, pageable);
        model.addAttribute("tags",tags);
        model.addAttribute("blogs",blogs);
        return "tags";
    }
}
