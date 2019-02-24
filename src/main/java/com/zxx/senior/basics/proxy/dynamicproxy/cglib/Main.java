package com.zxx.senior.basics.proxy.dynamicproxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

/**
 * @author: Aries
 * @create: 2018/12/01 01:35
 **/
public class Main {
    @Test
    public void test() {
        CglibProxy proxy = new CglibProxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(User.class);
        enhancer.setCallback(proxy);

        User user = (User) enhancer.create();
        user.sayByeBye();
    }
}
