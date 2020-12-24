package com.kong.blog.repository;

import com.kong.blog.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentReponsitory extends JpaRepository<Comment,Long> {
}
