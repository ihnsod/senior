package com.zxx.senior.ownspring.controller;

import com.zxx.senior.ownspring.annotation.ZAutowired;
import com.zxx.senior.ownspring.annotation.ZController;
import com.zxx.senior.ownspring.annotation.ZRequestMapping;
import com.zxx.senior.ownspring.annotation.ZRequestParam;
import com.zxx.senior.ownspring.pojo.User;
import com.zxx.senior.ownspring.service.VService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Aries
 * @create: 2018/12/03 21:47
 **/
@ZController
@ZRequestMapping("my")
public class VController {

    @ZAutowired("vServiceImpl")
    private VService vServiceImpl;

    @ZRequestMapping("user")
    public User getUser(HttpServletRequest request, HttpServletResponse response,
                        @ZRequestParam("age") Integer age, @ZRequestParam("name") String name) {

        return vServiceImpl.queryUser(age,name);
    }

}
