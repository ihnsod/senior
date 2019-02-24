package com.zxx.senior.thoroughspring.transactional.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: Ihnsod
 * @create: 2019/01/02 23:34
 **/
@Data
@Accessors(chain = true)
public class TransactionalBase {
    private Integer id;
    private String name;
}
