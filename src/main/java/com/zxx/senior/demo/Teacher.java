package com.zxx.senior.demo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: Ihnsod
 * @create: 2018/12/13 19:18
 **/
@Data
@Accessors(chain = true)
public class Teacher {

    private String name;

    private Integer age;

    private Integer sex;

}
