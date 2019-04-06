package com.Ihnsod.spring.pojo.transactional;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: Ihnsod
 * @create: 2019/03/07 23:46
 **/
@Data
@Accessors(chain = true)
public class Student {
    private Integer id;
    private String name;
    private String typeId;
    private String son;
}
