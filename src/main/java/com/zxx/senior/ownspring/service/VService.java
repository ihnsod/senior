package com.zxx.senior.ownspring.service;


import com.zxx.senior.ownspring.pojo.User;

/**
 * @author: Aries
 * @create: 2018/12/03 21:48
 **/
public interface VService {
    User queryUser(Integer age,String name);
}
