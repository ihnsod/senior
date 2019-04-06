package com.Ihnsod.spring.enums;

/**
 * @author: Ihnsod
 * @create: 2019/03/24 11:20
 **/
public enum BaseEnum implements IBaseEnum {

    SUCCESS(10000,"SUCCESS"),
    FAILURE(10001,"FAILURE");

    int code;
    String msg;

    BaseEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getMsg() {
        return null;
    }
}
