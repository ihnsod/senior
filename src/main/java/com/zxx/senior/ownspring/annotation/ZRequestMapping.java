package com.zxx.senior.ownspring.annotation;

import java.lang.annotation.*;

/**
 * @author: Aries
 * @create: 2018/12/03 21:41
 **/
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ZRequestMapping {
    String value() default "";
}
