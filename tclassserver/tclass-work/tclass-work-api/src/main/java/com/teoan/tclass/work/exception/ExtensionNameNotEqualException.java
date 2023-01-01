package com.teoan.tclass.work.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Teoan
 * @description
 * @date 2020/11/9 21:53
 */
public class ExtensionNameNotEqualException extends ResponseStatusException {
    public ExtensionNameNotEqualException(HttpStatus code, String msg) {
        super(code, msg);
    }
}