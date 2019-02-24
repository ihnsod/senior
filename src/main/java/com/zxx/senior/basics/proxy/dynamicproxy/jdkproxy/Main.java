package com.zxx.senior.basics.proxy.dynamicproxy.jdkproxy;

/**
 * @author: Aries
 * @create: 2018/11/25 23:12
 **/
public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        UserService proxy = (UserService) ProxyClient.getProxy(userService);

        System.err.println(proxy.sayHello("zxx"));

    }
}
