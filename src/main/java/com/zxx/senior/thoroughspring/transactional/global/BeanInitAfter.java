package com.zxx.senior.thoroughspring.transactional.global;

import org.springframework.beans.factory.InitializingBean;

/**
 * @author: Ihnsod
 * @create: 2019/02/23 12:16
 **/
public class BeanInitAfter implements InitializingBean {
    // 这个方法将在所有的属性被初始化后调用。
    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
