package com.zxx.senior.ownspring.service;

import com.zxx.senior.ownspring.annotation.ZService;
import com.zxx.senior.ownspring.pojo.User;

/**
 * @author: Aries
 * @create: 2018/12/03 21:48
 **/
@ZService("vServiceImpl")
public class VServiceImpl implements VService {

    @Override
    public User queryUser(Integer age, String name) {
        User user = new User();
        user.setAge(age);
        user.setName(name);
        return user;
    }
}
