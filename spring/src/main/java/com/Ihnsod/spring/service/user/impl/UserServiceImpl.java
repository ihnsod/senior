package com.Ihnsod.spring.service.user.impl;

import com.Ihnsod.spring.enums.user.UserExceptionEnum;
import com.Ihnsod.spring.exception.user.UserNotFindException;
import com.Ihnsod.spring.mapper.UserMapper;
import com.Ihnsod.spring.model.PageQuery;
import com.Ihnsod.spring.model.entity.user.User;
import com.Ihnsod.spring.model.entity.user.UserTest;
import com.Ihnsod.spring.service.user.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Ihnsod
 * @create: 2019/03/24 10:33
 **/
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public void editUser(User user) {
        logger.warn(user.toString());
        throw new UserNotFindException(UserExceptionEnum.USER_NOT_FIND);
    }

    @Override
    public PageInfo<UserTest> getUserByPage(PageQuery pageQuery) {
        PageHelper.startPage(pageQuery.getPageNum(),pageQuery.getPageSize());
        List<UserTest> allUser = userMapper.getAllUser();

        PageInfo<UserTest> pageInfo = new PageInfo<>(allUser);

        long total = pageInfo.getTotal();
        List<UserTest> list = pageInfo.getList();

         return pageInfo;
    }
}
