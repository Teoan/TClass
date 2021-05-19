package com.teoan.tclass.work.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Teoan
 * @description
 * @date 2020/11/10 21:51
 */
public class DirPathIsFileException extends ResponseStatusException {
    public DirPathIsFileException(HttpStatus code, String msg){
        super(code,msg);
    }
}
