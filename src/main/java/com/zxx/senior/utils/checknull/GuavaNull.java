package com.zxx.senior.utils.checknull;

import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.Test;

/**
 * @author: Ihnsod
 * @create: 2019/01/25 22:40
 **/
public class GuavaNull {


    @Test
    public void test() {

    }

    @Data
    @Immutable
    @Accessors(chain = true)
    class Sut {
        Integer id;
        String name;
    }
}
