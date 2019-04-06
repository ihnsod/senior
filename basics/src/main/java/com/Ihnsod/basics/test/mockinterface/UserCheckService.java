package com.Ihnsod.basics.test.mockinterface;

/**
 * @author: Ihnsod
 * @create: 2019/01/23 16:42
 **/
public interface UserCheckService {
    /**
     * 校验某个用户是否是合法用户
     *
     * @param userId 用户ID
     * @return 合法的就返回true, 否则返回false
     */
    boolean check(long userId);
}
