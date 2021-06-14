package com.wyx.blog.dao;

import com.wyx.blog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Create by WYX on 2021/6/3 22:35
 **/
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username,String password);


}














