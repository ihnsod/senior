package com.zxx.senior.basics.j8;

import lombok.Data;
import lombok.ToString;

/**
 * @author: Aries
 * @create: 2018/12/02 02:10
 **/
@Data
@ToString
public class User {

    public User(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    private Integer age;

    private String name;
}
