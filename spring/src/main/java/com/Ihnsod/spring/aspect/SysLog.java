package com.Ihnsod.spring.aspect;

import java.lang.annotation.*;

/**
 * @author: Ihnsod
 * @create: 2018/12/01 02:04
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default "";
}
