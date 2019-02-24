package com.zxx.senior.thoroughspring.aspectj;

import java.lang.annotation.*;

/**
 * @author: Aries
 * @create: 2018/12/01 02:04
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default "";
}
