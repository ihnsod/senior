package com.Ihnsod.spring.controller;

import com.Ihnsod.common.result.BaseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Ihnsod
 * @create: 2019/03/23 13:41
 **/
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/world")
    public BaseResult hello() {
        return BaseResult.successPojo("hello world ~~");
    }

}
