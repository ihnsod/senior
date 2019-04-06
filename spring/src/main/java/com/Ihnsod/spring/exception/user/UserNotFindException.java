package com.Ihnsod.spring.exception.user;

import com.Ihnsod.spring.enums.user.UserExceptionEnum;
import lombok.Data;

/**
 * @author: Ihnsod
 * @create: 2019/03/24 10:22
 **/
@Data
public class UserNotFindException extends RuntimeException {

    private int code;

    public UserNotFindException(UserExceptionEnum userExceptionEnum) {
        super(userExceptionEnum.getMsg());
        this.code = userExceptionEnum.getCode();
    }

    public UserNotFindException(String msg, Throwable tw) {
        super(msg, tw);
    }

    public UserNotFindException(Throwable tw) {
        super(tw);
    }
}
