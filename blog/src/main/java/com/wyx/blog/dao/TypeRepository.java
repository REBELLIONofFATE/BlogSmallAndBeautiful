package com.wyx.blog.dao;

import com.wyx.blog.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Create by WYX on 2021/6/8 9:11
 **/
public interface TypeRepository extends JpaRepository<Type,Long> {

    Type findByName(String name);

}
