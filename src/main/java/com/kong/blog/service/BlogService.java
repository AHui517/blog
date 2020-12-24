package com.kong.blog.service;

import com.kong.blog.domain.Blog;
import com.kong.blog.domain.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.List;

public interface BlogService {

    Blog getBlog(Long id);

    void saveBlog(Blog blog);

    void updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Page<Blog> listBlog(String search,Pageable pageable);

    List<Blog> listComment(Integer size);

    Page<Blog> findByTagId(Long id,Pageable pageable);
}
