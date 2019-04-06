package com.Ihnsod.spring.controller.user;

import com.Ihnsod.common.result.BaseResult;
import com.Ihnsod.common.result.CodeMsg;
import com.Ihnsod.spring.exception.user.UserNotFindException;
import com.Ihnsod.spring.model.PageQuery;
import com.Ihnsod.spring.model.entity.user.User;
import com.Ihnsod.spring.model.entity.user.UserTest;
import com.Ihnsod.spring.model.vo.UserVO;
import com.Ihnsod.spring.service.user.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author: Ihnsod
 * @create: 2019/03/23 22:46
 **/
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public BaseResult register(@RequestBody @Validated(UserVO.Create.class) UserVO userVO) {
        User user = userVO.convert(User.class);
        return BaseResult.successPojo(user);
    }

    @PostMapping("/edit")
    public BaseResult edit(@RequestBody @Validated(UserVO.Update.class) UserVO userVO) {
        User user = userVO.convert(User.class);
        return BaseResult.successPojo(user);
    }

    @PostMapping("/valid")
    public BaseResult valid(@RequestBody @Valid UserVO userVO) {
        User user = userVO.convert(User.class);
        return BaseResult.successPojo(user);
    }

    @PostMapping("/exception")
    public BaseResult exception(@RequestBody @Validated({UserVO.Update.class}) UserVO userVO) {
        User user = userVO.convert(User.class);
        try {
            userService.editUser(user);
        } catch (UserNotFindException e) {
            return BaseResult.error(CodeMsg.builder().msg(e.getMessage()).code(e.getCode()).build());
        }
        return BaseResult.successPojo(user);
    }

    @PostMapping("/page")
    public BaseResult page(@RequestBody PageQuery pageQuery) {

        PageInfo<UserTest> page =  userService.getUserByPage(pageQuery);

        return BaseResult.successPojo(page.getList());
    }
}
