package com.teoan.tclass.common.exception;

import com.teoan.tclass.common.result.R;
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
 * @description 全局异常处理
 * @date 2020/12/11 21:37
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

}
