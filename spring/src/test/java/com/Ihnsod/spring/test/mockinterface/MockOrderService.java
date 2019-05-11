package com.Ihnsod.spring.test.mockinterface;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author: Ihnsod
 * @create: 2019/01/23 16:54
 **/
public class MockOrderService {

    @Tested
    private OrderService orderService;

    long testUserId = 123456l;
    //测试商品id
    long testItemId = 456789l;

    // 绕过发送邮件服务和监测用户
    // 名服务来测试订单逻辑的正确性

    /**
     * @Tested表示被测试对象。如果该对象没有赋值，JMockit会去实例化它，若@Tested的构造函数有参数，
     * 则JMockit通过在测试属性&测试参数中查找@Injectable修饰的Mocked对象注入@Tested对象的构造函数来实例化，
     * 不然，则用无参构造函数来实例化。除了构造函数的注入，JMockit还会通过属性查找的方式，把@Injectable对象注入到@Tested对象中。
     *  注入的匹配规则：先类型，再名称(构造函数参数名，类的属性名)。若找到多个可以注入的@Injectable，则选择最优先定义的@Injectable对象。
     * 当然，我们的测试程序要尽量避免这种情况出现。因为给哪个测试属性/测试参数加@Injectable，是人为控制的。
     * @param mailService
     * @param userCheckService
     */
    @Test
    public void mockOrderService(@Injectable MailService mailService,
                                 @Injectable UserCheckService userCheckService) {

        new Expectations() {
            {
                mailService.sendMail(testUserId, anyString);
                result = true;

                userCheckService.check(testUserId);
                result = true;
            }

        };

        Assert.assertTrue(orderService.submitOrder(testUserId, testItemId));
    }

}
