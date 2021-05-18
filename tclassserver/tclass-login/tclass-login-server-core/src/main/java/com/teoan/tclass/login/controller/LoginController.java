package com.teoan.tclass.login.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Teoan
 * @date 2021/5/18 9:40
 */
@RestController
public class LoginController {

    @GetMapping("/test/test")
    public String test(){
        return "test";
    }
}
