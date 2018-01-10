package com.cloud.ecloud.oauthweb.controller;

import com.cloud.ecloud.oauthweb.CodedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * Created by xiongkw on 2018/1/9.
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CodedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object onCodedException(CodedException e) {
        HashMap<String, String> map = new HashMap<>(2);
        String code = e.getCode();
        map.put("code", code);
        String message = e.getMessage();
        map.put("message", message);
        logger.info("Request error, code: [{}], message: [{}]", code, message);
        return map;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object onException(Exception e) {
        logger.error("Internal server error!", e);
        return "Internal server error!";
    }
}
