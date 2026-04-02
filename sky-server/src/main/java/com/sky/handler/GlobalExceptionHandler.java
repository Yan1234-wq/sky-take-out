package com.sky.handler;

import com.sky.constant.MessageConstant;
import com.sky.exception.BaseException;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.aspectj.annotation.MetadataAwareAspectInstanceFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.LoginException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }
    /**
     *处理登录异常
     */
    @ExceptionHandler
    public Result loginExceptionHandler(SQLIntegrityConstraintViolationException ex){
        log.error("异常信息：{}", ex.getMessage());
        if (ex.getMessage().contains("Duplicate entry")) { // 处理重复 entries 的异常
            String[] split = ex.getMessage().split(" ");
            String msg = split[2] + MessageConstant.USERNAME_DUPLICATE;
            return Result.error(msg);
        }
        return Result.error(MessageConstant.UNKNOWN_ERROR);
    }
}
