package com.kong.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @RequestMapping("/blogs")
    public String blogs(){
        return "admin/blogs";
    }

}
