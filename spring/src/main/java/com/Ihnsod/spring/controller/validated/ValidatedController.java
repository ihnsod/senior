package com.Ihnsod.spring.controller.validated;

import com.Ihnsod.common.result.BaseResult;
import com.Ihnsod.spring.model.entity.Person;
import com.Ihnsod.spring.model.vo.UserVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author: Ihnsod
 * @create: 2019/03/23 17:21
 **/
@RestController
@RequestMapping("valid")
public class ValidatedController {

    @PostMapping("test1")
    public BaseResult validOne(@RequestBody @Valid Person person) {
        return BaseResult.successPojo(person);
    }

    @PostMapping("test2")
    public BaseResult validTwo(@RequestBody @Validated(UserVO.Update.class) UserVO userVO) {
        return BaseResult.successPojo(userVO);
    }
}
