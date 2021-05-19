package com.teoan.tclass.notice.controller;

import com.teoan.tclass.notice.entity.Notice;
import com.teoan.tclass.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Teoan
 * @date 2021/5/18 11:41
 */
@RestController
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @GetMapping("/test")
    public Notice test(){
        return noticeService.getCurrentNotice();
    }
}
