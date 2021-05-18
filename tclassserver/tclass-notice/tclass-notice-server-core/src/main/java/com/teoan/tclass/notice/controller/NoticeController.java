package com.teoan.tclass.notice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Teoan
 * @date 2021/5/18 11:41
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
