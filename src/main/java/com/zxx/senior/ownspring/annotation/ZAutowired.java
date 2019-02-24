package com.zxx.senior.ownspring.annotation;

import java.lang.annotation.*;

/**
 * @author: Aries
 * @create: 2018/12/03 21:40
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ZAutowired {
    String value() default "";
}
