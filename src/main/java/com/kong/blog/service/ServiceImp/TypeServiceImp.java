package com.kong.blog.service.ServiceImp;

import com.kong.blog.domain.Type;
import com.kong.blog.repository.TypeRepositoty;
import com.kong.blog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TypeServiceImp implements TypeService {

    @Autowired
    private TypeRepositoty typeRepositoty;

    @Override
    @Transactional
    public Type saveType(Type type) {
        return typeRepositoty.save(type);
    }

    @Override
    @Transactional
    public Type getType(Long id) {
        return typeRepositoty.getOne(id);
    }

    @Override
    @Transactional
    public Page<Type> listType(Pageable pageable) {
        return typeRepositoty.findAll(pageable);
    }

    @Override
    @Transactional
    public Type updateType(Long id, Type type) {
        Type one = typeRepositoty.getOne(id);
        BeanUtils.copyProperties(type,one);
        return typeRepositoty.save(one);
    }

    @Override
    @Transactional
    public void deleteType(Long id) {
        typeRepositoty.deleteById(id);
    }

    @Override
    public Type findByName(String name) {
        Type byName = typeRepositoty.findByName(name);
        return byName;
    }

    @Override
    public Type findById(Long id) {
        return typeRepositoty.getOne(id);
    }

    @Override
    public List<Type> findAll() {
        return typeRepositoty.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = PageRequest.of(0, size, sort);
        return typeRepositoty.findTop(pageable);
    }
}
