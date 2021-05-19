package com.teoan.tclass.work.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;

/**
 * @author Teoan
 * @description
 * @date 2020/11/11 22:09
 */
public class FileNotExistsException extends ResponseStatusException {
    public FileNotExistsException(HttpStatus code,String msg){
        super(code,msg);
    }
}