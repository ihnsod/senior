package com.Ihnsod.spring.enums.user;

import com.Ihnsod.spring.enums.IBaseEnum;

/**
 * @author: Ihnsod
 * @create: 2019/03/24 10:35
 **/
public enum UserExceptionEnum implements IBaseEnum {

    USER_NOT_FIND(10001, "用户不存在!"),
    USER_ALREADY_EXISTS(10002, "用户已存在!");

    int code;
    String msg;


    UserExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
