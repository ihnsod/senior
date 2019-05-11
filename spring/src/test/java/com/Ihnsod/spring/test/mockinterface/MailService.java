package com.Ihnsod.spring.test.mockinterface;

/**
 * @author: Ihnsod
 * @create: 2019/01/23 16:41
 **/
public interface MailService {

    /**
     * 发送邮件接口 发送成功返回true 发送失败返回false
     * @param userId
     * @param content
     * @return
     */
    boolean sendMail(long userId, String content);
}
