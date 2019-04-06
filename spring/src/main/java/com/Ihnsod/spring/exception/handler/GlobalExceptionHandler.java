package com.Ihnsod.spring.exception.handler;

import com.Ihnsod.common.result.BaseResult;
import com.Ihnsod.common.result.CodeMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Ihnsod
 * @create: 2019/03/23 17:42
 **/
@Order(2)
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public BaseResult globalExceptionDeal(Exception exception, HttpServletRequest request) {
        logger.warn("请求出错,请求URL{}", request.getRequestURL());
        logger.warn("错误信息{}", exception.getMessage());
        System.err.println("全局异常" + exception.getMessage());
        return BaseResult.error(CodeMsg.builder().code(10010).msg(exception.getMessage()).build());
    }
}
