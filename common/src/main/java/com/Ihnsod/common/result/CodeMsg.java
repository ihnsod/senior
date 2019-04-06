package com.Ihnsod.common.result;

import lombok.Builder;
import lombok.Data;

/**
 * @author: Ihnsod
 * @create: 2018/12/12 17:39
 **/
@Data
@Builder
public class CodeMsg {

    private Integer code;

    private String msg;

}
