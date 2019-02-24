package com.zxx.senior.thoroughspring.transactional.dao;

import com.zxx.senior.thoroughspring.transactional.pojo.TransactionalBase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author: Ihnsod
 * @create: 2019/01/02 22:38
 **/
@Mapper
public interface TransactionalTwoMapper {
    void transactionalOne(@Param("trans") TransactionalBase transactionalBase);

    void transactionalTwo(@Param("trans") TransactionalBase transactionalBase);

    void transactionalThree(@Param("trans") TransactionalBase transactionalBase);
}
