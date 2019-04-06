//package com.Ihnsod.spring.service.transactional.impl;
//
//
//import com.Ihnsod.spring.mapper.transactional.TransactionalOneMapper;
//import com.Ihnsod.spring.pojo.transactional.TransactionalOne;
//import com.Ihnsod.spring.service.transactional.TransactionalServiceOne;
//import com.Ihnsod.spring.service.transactional.TransactionalServiceThree;
//import com.Ihnsod.spring.service.transactional.TransactionalServiceTwo;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// * @author: Ihnsod
// * @create: 2019/01/02 22:34
// **/
//@Slf4j
//@Service
//public class TransactionalServiceOneImpl implements TransactionalServiceOne {
//
//    @Autowired
//    private TransactionalServiceTwo transactionalServiceTwo;
//
//    @Autowired
//    private TransactionalServiceThree transactionalServiceThree;
//
//    @Autowired
//    private TransactionalOneMapper transactionalOneMapper;
//
//    /**
//     * 测试事务demo1
//     */
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED)
//    public void transactionalOne() {
//        //调用自身service 下的mapper
//        transactionalOneMapper.transactionalOne(new TransactionalOne().setName("transactionalOne"));
//        //调用service2 服务下的方法，该方法的事务管理也是 required
//        try {
//            transactionalServiceTwo.transactionalOne();
//        } catch (RuntimeException e) {
//            log.error("测试事务情况");
//        }
//        //调用service3 服务下的方法，该方法的事务管理也是 required
//        transactionalServiceThree.transactionalOne();
//        //在调用者方法内抛出异常 则都会回滚
////        throw new RuntimeException();
//
//    }
//
//    @Override
//    @Transactional(propagation = Propagation.SUPPORTS)
//    public void transactionalTwo() {
//        //调用自身service 下的mapper
//        transactionalOneMapper.transactionalOne(new TransactionalOne().setName("transactionalOne"));
//        //调用service2 服务下的方法，该方法的事务管理也是 required
////        try {
////            transactionalServiceTwo.transactionalTwo();
////        } catch (RuntimeException e) {
////            log.error("测试事务情况");
////        }
//        //从调用的方法中抛出了异常 此方法不会被回滚 因为此方法为第一入口 没有事务
//        //如果下面的方法开启了事务 则该方法会回滚 同时 紧接着 service3 如果对于事务的支持是 SUPPORTS 或者是 REQUIRED
//        //service3 的事务也会回滚
//        transactionalServiceTwo.transactionalTwo();
//        //调用service3 服务下的方法，该方法的事务管理也是 required
//        transactionalServiceThree.transactionalTwo();
//        //在调用者方法内抛出异常 Propagation.SUPPORTS 如果调用此方法的对象没有事务
//        //则在此方法中抛出了异常事务不会回滚
//        throw new RuntimeException();
//    }
//
//    @Override
//    public void transactionalThree() {
//
//    }
//}
