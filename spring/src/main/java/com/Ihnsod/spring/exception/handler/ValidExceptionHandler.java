package com.Ihnsod.spring.exception.handler;

import com.Ihnsod.common.result.BaseResult;
import com.Ihnsod.common.result.CodeMsg;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @author: Ihnsod
 * @create: 2019/03/23 17:32
 **/
@Order(1)
@ResponseBody
@RestControllerAdvice
public class ValidExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResult validationException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();

        FieldError fieldError = bindingResult.getFieldError();

        System.err.println(fieldError.getDefaultMessage());

        return BaseResult.error(CodeMsg.builder().code(10004).msg(fieldError.getDefaultMessage()).build());
    }
}
