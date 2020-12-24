package com.kong.blog.controller;

import com.kong.blog.domain.*;
import com.kong.blog.service.BlogService;
import com.kong.blog.service.CommentService;
import com.kong.blog.service.TagService;
import com.kong.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 5,sort = {"id",}) Pageable pageable, Model model){
        Page<Blog> blogs = blogService.listBlog(pageable, new BlogQuery());
        List<Type> types = typeService.listTypeTop(5);
        List<Tag> tags = tagService.findTop(5);
        List<Blog> blogComment = blogService.listComment(4);
//        获取全部博客进行展示
        model.addAttribute("blogs",blogs);
        model.addAttribute("types",types);
        model.addAttribute("tags",tags);
        model.addAttribute("blogComment",blogComment);
        return "index";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 5,sort = {"id",}) Pageable pageable, Model model, String search) {
//        根据关键字查询博客（查询类容和标题）
        Page<Blog> searchQuery = blogService.listBlog("%"+search+"%", pageable);
        model.addAttribute("searchQuery",searchQuery);
        model.addAttribute("search",search);
        return "search";
    }

//    跳转到博客详情
    @GetMapping("/detailed/{id}")
    public String blogDetailed(@PathVariable Long id,Model model){
        Blog blog = blogService.getBlog(id);
        model.addAttribute("blog",blog);
        return "blog";
    }

    //评论
    @PostMapping("/savacontent")
    public String savecontent(Long id,String comment){
        Blog blog = blogService.getBlog(id);
        Comment c=new Comment();
        c.setAvatar("https://i.ibb.co/ggRGDxN/img-dcd3e5ab71dea5f83b4ea25eee39c198-jpg.jpg");
        c.setBlog(blog);
        c.setContent(comment);
        c.setCreateTime(new Date());
        commentService.savacommet(c);
        return "redirect:/detailed/"+blog.getId();
    }

//    跳转到总标签界面
    @GetMapping("/tags")
    public String tags(){
        return "tags";
    }

    //    跳转到总分类界面
    @GetMapping("/types")
    public String types(){
        return "types";
    }
}
