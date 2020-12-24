package com.kong.blog.service.ServiceImp;

import com.kong.blog.domain.Comment;
import com.kong.blog.repository.CommentReponsitory;
import com.kong.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    private CommentReponsitory commentReponsitory;

    @Override
    public void savacommet(Comment comment) {
        commentReponsitory.save(comment);
    }
}
