package com.teoan.tclass.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Teoan
 * @description
 * @date 2020/12/11 21:37
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxFileSize;
    //捕获文件大小异常
    @ExceptionHandler(MultipartException.class)
    public ResponseEntity exceptionHandler() throws ResponseStatusException{
        Map<String,Object> map = new HashMap<>();
        map.put("message","上传失败,文件超过"+maxFileSize+"!");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
    }
}
