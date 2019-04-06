//package com.Ihnsod.spring.service.transactional.impl;
//
//
//import com.Ihnsod.spring.mapper.transactional.TransactionalThreeMapper;
//import com.Ihnsod.spring.pojo.transactional.TransactionalThree;
//import com.Ihnsod.spring.service.transactional.TransactionalServiceThree;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// * @author: Ihnsod
// * @create: 2019/01/02 22:34
// **/
//@Service
//public class TransactionalServiceThreeImpl implements TransactionalServiceThree {
//
//    @Autowired
//    private TransactionalThreeMapper transactionalThreeMapper;
//
//    @Override
//    public void transactionalOne() {
//        transactionalThreeMapper.transactionalOne(
//                new TransactionalThree().setName("transactionalThree"));
//    }
//
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED)
//    public void transactionalTwo() {
//        transactionalThreeMapper.transactionalTwo(
//                new TransactionalThree().setName("transactionalThree"));
//    }
//
//    @Override
//    public void transactionalThree() {
//
//    }
//}
