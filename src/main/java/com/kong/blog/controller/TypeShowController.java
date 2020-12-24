package com.kong.blog.controller;

import com.kong.blog.domain.Blog;
import com.kong.blog.domain.BlogQuery;
import com.kong.blog.domain.Type;
import com.kong.blog.service.BlogService;
import com.kong.blog.service.TypeService;
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
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String showTypes(@PathVariable Long id, @PageableDefault(size = 100,sort = "updateTime") Pageable pageable, Model model){
//        获取所有标签
        List<Type> types = typeService.findAll();
        if (id==-1){
            id=types.get(0).getId();
            System.out.println(id);
        }
//        这里BlogQuery的 用于查询一类标签
        BlogQuery bq = new BlogQuery();
        bq.setTypeId(id);
        Page<Blog> blogs = blogService.listBlog(pageable, bq);
        model.addAttribute("types",types);
        model.addAttribute("blogs",blogs);
        return "types";
    }
}
