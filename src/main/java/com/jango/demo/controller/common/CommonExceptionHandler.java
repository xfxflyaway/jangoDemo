package com.jango.demo.controller.common;

import com.jango.demo.domain.BaseResp;
import com.jango.demo.exception.JangoAppException;
import com.jango.demo.exception.JangoAppRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiongfeixiang
 * @title ExceptionHandler
 * @date 2018/2/1 11:28
 * @since v1.0.0
 */
@RestController
@ControllerAdvice
public class CommonExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(CommonExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public BaseResp exceptionHandler(Exception e) {
        log.error("", e);
        return new BaseResp(500, "服务器异常");
    }

    @ExceptionHandler(JangoAppRuntimeException.class)
    public BaseResp apiExceptionHandler(JangoAppRuntimeException e) {
        log.error("", e);
        return new BaseResp(e);
    }

    @ExceptionHandler(JangoAppException.class)
    public BaseResp appExceptionHandler(JangoAppException e) {
        log.error("", e);
        return new BaseResp(e);
    }

}
