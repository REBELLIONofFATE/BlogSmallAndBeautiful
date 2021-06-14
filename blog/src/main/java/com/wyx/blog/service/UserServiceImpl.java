package com.wyx.blog.service;

import com.wyx.blog.dao.UserRepository;
import com.wyx.blog.po.User;
import com.wyx.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by WYX on 2021/6/3 22:32
 **/

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}











