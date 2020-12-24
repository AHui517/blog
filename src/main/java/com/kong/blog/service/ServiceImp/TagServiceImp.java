package com.kong.blog.service.ServiceImp;

import com.kong.blog.domain.Tag;
import com.kong.blog.repository.TagRepository;
import com.kong.blog.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImp implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    @Transactional
    public Tag getTag(Long id) {
        return tagRepository.getOne(id);
    }

    @Override
    @Transactional
    public void saveTag(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    @Transactional
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateTag(Long id, Tag tag) {
        Tag t = getTag(id);
        BeanUtils.copyProperties(tag,t);
        tagRepository.save(t);
    }
    @Transactional
    @Override
    public Page<Tag> getAll(Pageable pageable){
        return tagRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Tag findByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    @Transactional
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    @Transactional
    public List<Tag> listTag(String str)  {
        return tagRepository.findAllById(stringToList(str));
    }

    @Override
    public List<Tag> findTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = PageRequest.of(0, size, sort);
        return tagRepository.findTop(pageable);
    }


    private List<Long> stringToList(String str){
        String[] strs=str.split(",");
        List<Long> list=new ArrayList<>();
        for (String s:strs){
            list.add(Long.parseLong(s));
        }
        return list;
    }
}
