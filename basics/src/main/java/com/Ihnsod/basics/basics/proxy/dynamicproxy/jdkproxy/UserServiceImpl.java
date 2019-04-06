package com.Ihnsod.basics.basics.proxy.dynamicproxy.jdkproxy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author: Ihnsod
 * @create: 2018/11/25 23:09
 **/
public class UserServiceImpl implements UserService {
    public String sayHello(String msg) {
        return msg + "你好啊，现在的时间是" + DateTimeFormatter.ofPattern("yyyy-hh-dd HH:mm:ss")
                .format(LocalDateTime.now());
    }
}
