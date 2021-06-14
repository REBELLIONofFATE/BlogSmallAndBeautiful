package com.wyx.blog.dao;

import com.wyx.blog.po.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Create by WYX on 2021/6/14 8:37
 **/
public interface TagRepository extends JpaRepository<Tag,Long> {

    Tag findByName(String name);
}
