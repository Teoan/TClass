package com.teoan.tclass.work.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Teoan
 * @date 2020/11/11 20:51
 */
public class DeleteFileException extends ResponseStatusException {

    public DeleteFileException(HttpStatus code, String msg) {
        super(code, msg);
    }

}