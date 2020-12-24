package com.kong.blog.service;

import com.kong.blog.domain.User;

public interface UserService {
    public User checkUser(String username,String password);
}
