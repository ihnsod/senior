package com.Ihnsod.basics.test.mockinterface;

import mockit.Expectations;
import mockit.Injectable;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author: Ihnsod
 * @create: 2019/01/23 15:23
 **/
public class MockInter {

    // 在接口中使用此此注解可以为接口生成一个虚拟的实现类
    // 如果此接口有实现类也不会调用此实现类中实现的方法
    @Injectable
    private InterfaceOne interfaceOne;

    // 直接使用此接口进行测试方法
    @Test
    public void mock1() {
        int numberOne = interfaceOne.getNumberOne();
        System.err.println(numberOne);
    }

    // 使用 Expectations 类来进行返回结果的设定与断言
    @Test
    public void mock2() {
        new Expectations() {
            {
                interfaceOne.getNumberOne();
                result = 1;
                interfaceOne.getNumberTwo();
                result = 0;
            }
        };
        Assert.assertEquals(1, interfaceOne.getNumberOne());
        Assert.assertEquals(0, interfaceOne.getNumberTwo());
    }
}
