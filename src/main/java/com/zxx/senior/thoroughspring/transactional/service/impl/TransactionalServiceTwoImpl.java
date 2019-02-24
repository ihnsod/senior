package com.zxx.senior.thoroughspring.transactional.service.impl;

import com.zxx.senior.thoroughspring.transactional.dao.TransactionalTwoMapper;
import com.zxx.senior.thoroughspring.transactional.pojo.TransactionalTwo;
import com.zxx.senior.thoroughspring.transactional.service.TransactionalServiceTwo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Ihnsod
 * @create: 2019/01/02 22:34
 **/
@Service
@Slf4j
public class TransactionalServiceTwoImpl implements TransactionalServiceTwo {

    @Autowired
    private TransactionalTwoMapper transactionalMapper2;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionalOne() {

        transactionalMapper2.transactionalOne(
                new TransactionalTwo().setName("transactionalOneName"));

        //在此方法中抛出了的异常，如果此方法try catch 住了则不会触发回滚
        //否则抛到了上层就算上层try catch 了也会回滚
        throw new RuntimeException("测试事务回滚");

//        try {
//            throw new RuntimeException("测试try catch");
//        } catch (RuntimeException e) {
//            log.error("捕获异常了！");
//        }
    }

    @Override
//    @Transactional(propagation = Propagation.REQUIRED)
    @Transactional(propagation = Propagation.SUPPORTS)
    public void transactionalTwo() {
        transactionalMapper2.transactionalTwo(
                new TransactionalTwo().setName("transactionalOneName"));

        //在此方法中抛出了的异常，如果此方法try catch 住了则不会触发回滚
        //否则抛到了上层就算上层try catch 了也会回滚
//        throw new RuntimeException("测试事务回滚");
    }

    @Override
    public void transactionalThree() {

    }
}
