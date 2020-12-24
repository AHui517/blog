package com.kong.blog.service.ServiceImp;

import com.kong.blog.service.UserService;
import com.kong.blog.domain.User;
import com.kong.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class  UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User checkUser(String username,String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        return user;
    }
}
