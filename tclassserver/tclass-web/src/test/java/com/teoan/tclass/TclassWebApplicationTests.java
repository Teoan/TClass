package com.teoan.tclass;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teoan.tclass.entity.Student;
import com.teoan.tclass.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class TclassWebApplicationTests {


    @Resource
    StudentMapper studentMapper;

    @Test
    void contextLoads() {


        Page<Student> page = new Page<>(2,5);

        Student student = Student.builder().gender("女").build();
        QueryWrapper<Student> wrapper = new QueryWrapper<>(student);
        IPage<Student> studentIPage = studentMapper.selectPage(page, wrapper);
        List<Student> studentList = studentIPage.getRecords();

        studentList.forEach(System.out::println);
    }

}
