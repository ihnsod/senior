package com.Ihnsod.spring.enums.user;

/**
 * @author: Ihnsod
 * @create: 2019/03/23 22:39
 **/
public enum  StatusEnum {
    NORMAL(0), DISABLE(1);

    private final int value;

    StatusEnum(final int value) {
        this.value = value;
    }
}
