package com.wyx.blog.service;

import com.wyx.blog.po.User;

/**
 * Create by WYX on 2021/6/3 22:30
 **/
public interface UserService {

    User checkUser(String username,String password);

}
