package com.kong.blog.service;

import com.kong.blog.domain.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {
    Type saveType(Type type);

    Type getType(Long id);

    Page<Type> listType(Pageable pageable);

    Type updateType(Long id,Type type);

    void deleteType(Long id);

    Type findByName(String name);

    Type findById(Long id);

    List<Type> findAll();

    List<Type> listTypeTop(Integer size);
}
