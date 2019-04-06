package com.Ihnsod.spring.enums;

/**
 * @author: Ihnsod
 * @create: 2019/03/24 11:19
 **/
public enum ParamErrorEnum implements IBaseEnum{
    SUCCESS(10004,"SUCCESS");

    int code;
    String msg;

    ParamErrorEnum(int code,String msg){
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
