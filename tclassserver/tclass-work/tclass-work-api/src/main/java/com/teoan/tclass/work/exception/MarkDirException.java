package com.teoan.tclass.work.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Teoan
 * @date 2020/11/10 22:32
 */
public class MarkDirException extends ResponseStatusException {
    public MarkDirException(HttpStatus code, String msg) {
        super(code, msg);
    }
}
