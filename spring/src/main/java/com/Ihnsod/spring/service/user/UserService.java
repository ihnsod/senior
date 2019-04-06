package com.Ihnsod.spring.service.user;

import com.Ihnsod.spring.model.PageQuery;
import com.Ihnsod.spring.model.entity.user.User;
import com.Ihnsod.spring.model.entity.user.UserTest;
import com.github.pagehelper.PageInfo;

/**
 * @author: Ihnsod
 * @create: 2019/03/24 10:32
 **/
public interface UserService {

    void editUser(User user);

    PageInfo<UserTest> getUserByPage(PageQuery pageQuery);
}
