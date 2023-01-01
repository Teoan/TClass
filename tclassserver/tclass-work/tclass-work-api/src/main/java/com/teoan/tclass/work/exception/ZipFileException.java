package com.teoan.tclass.work.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Teoan
 * @date 2020/11/14 15:40
 */
public class ZipFileException extends ResponseStatusException {
    public ZipFileException(HttpStatus code, String msg) {
        super(code, msg);
    }
}
