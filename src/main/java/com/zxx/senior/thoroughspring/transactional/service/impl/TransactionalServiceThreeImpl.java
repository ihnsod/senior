package com.zxx.senior.thoroughspring.transactional.service.impl;

import com.zxx.senior.thoroughspring.transactional.dao.TransactionalThreeMapper;
import com.zxx.senior.thoroughspring.transactional.pojo.TransactionalThree;
import com.zxx.senior.thoroughspring.transactional.service.TransactionalServiceThree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Ihnsod
 * @create: 2019/01/02 22:34
 **/
@Service
public class TransactionalServiceThreeImpl implements TransactionalServiceThree {

    @Autowired
    private TransactionalThreeMapper transactionalThreeMapper;

    @Override
    public void transactionalOne() {
        transactionalThreeMapper.transactionalOne(
                new TransactionalThree().setName("transactionalThree"));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transactionalTwo() {
        transactionalThreeMapper.transactionalTwo(
                new TransactionalThree().setName("transactionalThree"));
    }

    @Override
    public void transactionalThree() {

    }
}
