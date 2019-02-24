package com.zxx.senior.thoroughspring.aspectj;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author: Aries
 * @create: 2018/12/01 01:44
 **/
@Slf4j
@Aspect
@Component
public class LogAspectJ {

    @Pointcut("@annotation(com.zxx.senior.thoroughspring.aspectj.SysLog)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        long time = System.currentTimeMillis() - beginTime;
        log.info("时间：" + time);
        Object result = point.proceed();
        log.info("result" + result);
        return result;
    }

}
