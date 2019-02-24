package com.zxx.senior.demo;

import com.zxx.senior.test.mockinterface.InterfaceOne;
import mockit.Expectations;
import mockit.Injectable;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author: Ihnsod
 * @create: 2019/01/22 20:36
 * jmock 测试接口
 **/
public class MockDemo1 {

    @Injectable
    private InterfaceOne interfaceOne;

    @Test
    public void mock() {

        new Expectations() {
            {
                interfaceOne.getNumberOne();
                result = 1;
                interfaceOne.getNumberTwo();
                result = 2;
            }
        };
        Assert.assertEquals(1, interfaceOne.getNumberOne());
        Assert.assertEquals(2, interfaceOne.getNumberTwo());
    }
}
