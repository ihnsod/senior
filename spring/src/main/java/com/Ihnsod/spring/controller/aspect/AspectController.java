package com.Ihnsod.spring.controller.aspect;

import com.Ihnsod.spring.aspect.SysLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Ihnsod
 * @create: 2018/12/01 02:14
 **/
@RestController
@RequestMapping("aspectj")
public class AspectController {

    @SysLog("测试切面")
    @RequestMapping("test")
    public String aspectj(@RequestParam("name") String name) {
        return name;
    }
}
