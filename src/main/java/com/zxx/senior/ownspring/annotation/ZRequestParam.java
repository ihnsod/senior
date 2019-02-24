package com.zxx.senior.ownspring.annotation;

import java.lang.annotation.*;

/**
 * @author: Aries
 * @create: 2018/12/03 21:44
 **/

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ZRequestParam {
    String value() default "";
}
