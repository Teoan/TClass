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

        IPage<Student> studentIPage = studentMapper.selectPage(page, null);
        List<Student> studentList = studentIPage.getRecords();

        studentList.forEach(System.out::println);
    }

}
