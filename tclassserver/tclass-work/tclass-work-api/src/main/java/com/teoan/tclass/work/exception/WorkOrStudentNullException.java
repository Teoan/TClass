package com.teoan.tclass.work.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Teoan
 * @description
 * @date 2020/11/9 22:09
 */
public class WorkOrStudentNullException extends ResponseStatusException {
    public WorkOrStudentNullException(HttpStatus code, String msg) {
        super(code, msg);
    }
}
