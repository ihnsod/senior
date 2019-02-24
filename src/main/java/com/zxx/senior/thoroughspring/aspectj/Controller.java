package com.zxx.senior.thoroughspring.aspectj;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Aries
 * @create: 2018/12/01 02:14
 **/
@RestController
@RequestMapping("aspectj")
public class Controller {

    @SysLog("测试切面")
    @RequestMapping("test")
    public String aspectj(@RequestParam("name") String name) {
        return name;
    }
}
