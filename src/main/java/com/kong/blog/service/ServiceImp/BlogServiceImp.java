package com.kong.blog.service.ServiceImp;

import com.kong.blog.domain.Blog;
import com.kong.blog.domain.BlogQuery;
import com.kong.blog.domain.Type;
import com.kong.blog.repository.BlogResponsitory;
import com.kong.blog.service.BlogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImp implements BlogService {

    @Autowired
    private BlogResponsitory blogResponsitory;

    @Override
    public Blog getBlog(Long id) {
        return blogResponsitory.getOne(id);
    }

    @Override
    public void saveBlog(Blog blog) {
        if (blog.getId()!=0){
            blog.setUpdateTime(new Date());
            blog.setViews(blogResponsitory.getOne(blog.getId()).getViews());
            blog.setCreateTime(blogResponsitory.getOne(blog.getId()).getCreateTime());
            blogResponsitory.save(blog);
        }
        else {
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
            blogResponsitory.save(blog);
        }
    }

    @Override
    public void updateBlog(Long id, Blog blog) {
        Blog one = blogResponsitory.getOne(id);
        BeanUtils.copyProperties(one, blog);
        blogResponsitory.save(one);
    }

    @Override
    public void deleteBlog(Long id) {
        blogResponsitory.deleteById(id);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        return blogResponsitory.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                ArrayList<Predicate> predicates = new ArrayList<>();
                if (blog.getTitle() != null && !"".equals(blog.getTitle())) {
                    predicates.add(cb.like(root.<String>get("title"), "%" + blog.getTitle() + "%"));
                }
                if (blog.getTypeId() != null) {
                    predicates.add(cb.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
                }
                if (blog.isRecommend()) {
                    predicates.add(cb.equal(root.get("recommend"), blog.isRecommend()));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        }, pageable);
    }

    @Override
    public Page<Blog> listBlog(String search, Pageable pageable) {
        Page<Blog> searchQuery = blogResponsitory.findByQuery(search, pageable);
        return searchQuery;
    }

    @Override
    public List<Blog> listComment(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
        Pageable pageable = PageRequest.of(0, size, sort);
        return blogResponsitory.findTop(pageable);
    }

    @Override
    public Page<Blog> findByTagId(Long id,Pageable pageable) {
        return blogResponsitory.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Join join = root.join("tags");
                return cb.equal(join.get("id"),id);
            }
        },pageable);
    }
}