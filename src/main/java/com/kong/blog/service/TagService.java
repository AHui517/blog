package com.kong.blog.service;

import com.kong.blog.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    Tag getTag(Long id);

    void saveTag(Tag tag);

    void deleteTag(Long id);

    void updateTag(Long id,Tag tag);

    Page<Tag> getAll(Pageable pageable);

    Tag findByName(String name);

    List<Tag> findAll();

    List<Tag> listTag(String str);

    List<Tag> findTop(Integer size);
}
