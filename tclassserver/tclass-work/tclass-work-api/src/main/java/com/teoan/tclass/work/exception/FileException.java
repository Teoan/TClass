package com.teoan.tclass.work.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Teoan
 * @description 文件异常
 * @date 2020/11/8 20:47
 */
public class FileException extends ResponseStatusException {
    public FileException(HttpStatus code, String msg) {
        super(code, msg);
    }
}