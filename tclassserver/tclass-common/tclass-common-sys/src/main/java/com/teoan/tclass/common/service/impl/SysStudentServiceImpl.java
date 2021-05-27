package com.teoan.tclass.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.common.mapper.StudentMapper;
import com.teoan.tclass.common.entity.Student;
import com.teoan.tclass.common.service.SysStudentService;
import org.springframework.stereotype.Service;

/**
 * (Student)表服务实现类
 *
 * @author Teoan
 * @since 2021-05-19 16:49:40
 */
@Service("sysStudentService")
public class SysStudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements SysStudentService {

}
