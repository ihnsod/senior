package com.Ihnsod.spring.mapper;

import com.Ihnsod.spring.model.entity.user.UserTest;

import java.util.List;

/**
 * @author: Ihnsod
 * @create: 2019/03/24 22:01
 **/
public interface UserMapper{
    List<UserTest> getAllUser();
}