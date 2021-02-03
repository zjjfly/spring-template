package com.github.zjjfly.foo.controller;

import com.github.zjjfly.foo.constraint.ExceptionCode;
import com.github.zjjfly.foo.domain.BizException;
import com.github.zjjfly.foo.domain.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * restful controller advisor
 *
 * @author zjjfly[https://github.com/zjjfly]
 * @date 2021-02-02
 */
@RestControllerAdvice
@Slf4j
public class RestAdvisor {

    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResult<Void> handleBizException(BizException e) {
        return RestResult.error(e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResult<Void> handleUnexpectedException(Exception e) {
        log.error(e.getMessage(), e);
        return RestResult.error(ExceptionCode.UNEXPECTED_ERROR);
    }

}
