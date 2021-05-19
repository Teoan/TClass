package com.teoan.tclass.user.controller;

import com.teoan.tclass.user.entity.Student;
import com.teoan.tclass.user.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Teoan
 * @date 2021/5/19 17:07
 */
@RestController
public class StudentController {

    @Autowired
    StudentService studentService;
    @GetMapping("/test")

    public List<Student> test(){
        return studentService.list();
    }
}
