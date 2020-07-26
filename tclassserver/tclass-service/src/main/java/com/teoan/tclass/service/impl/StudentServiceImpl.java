package com.teoan.tclass.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.entity.Student;
import com.teoan.tclass.mapper.StudentMapper;
import com.teoan.tclass.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * (Student)表服务实现类
 *
 * @author Teoan
 * @since 2020-07-25 10:14:33
 */
@Service("studentService")
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}