package com.teoan.tclass.work.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Teoan
 * @date 2020/11/26 21:59
 */
public class ExtensionIsExists extends ResponseStatusException {
    public ExtensionIsExists(HttpStatus code,String msg){
        super(code,msg);
    }
}
