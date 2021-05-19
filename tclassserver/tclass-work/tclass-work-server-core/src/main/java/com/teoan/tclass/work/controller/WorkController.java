package com.teoan.tclass.work.controller;

import com.teoan.tclass.work.entity.Work;
import com.teoan.tclass.work.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Teoan
 * @description
 * @date 2021/5/19 22:48
 */
@RestController
public class WorkController {
    @Autowired
    WorkService workService;


    @GetMapping("/test")
    public Work test(){
        return workService.getById("1");
    }
}
