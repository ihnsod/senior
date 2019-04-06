package com.Ihnsod.basics.test.mockinterface;

/**
 * @author: Ihnsod
 * @create: 2019/01/23 16:54
 **/
public class OrderService {

    MailService mailService;
    // 用户身份校验类，用于校验某个用户是不是合法用户
    UserCheckService userCheckService;

    // 构造函数
//    public OrderService(MailService mailService) {
//        this.mailService = mailService;
//    }


    public boolean submitOrder(long buyerId, long itemId) {
        // 先校验用户身份
        if (!userCheckService.check(buyerId)) {
            // 用户身份不合法
            return false;
        }
        // 下单逻辑代码，
        // 省略...
        // 下单完成，给买家发邮件
        return this.mailService.sendMail(buyerId, "下单成功");
    }
}
