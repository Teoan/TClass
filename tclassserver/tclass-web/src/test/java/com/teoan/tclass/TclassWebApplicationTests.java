package com.teoan.tclass;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teoan.tclass.entity.Role;
import com.teoan.tclass.entity.Student;
import com.teoan.tclass.mapper.NoticeMapper;
import com.teoan.tclass.mapper.RoleMapper;
import com.teoan.tclass.mapper.StudentMapper;
import com.teoan.tclass.service.FileService;
import com.teoan.tclass.service.NoticeService;
import com.teoan.tclass.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
class TclassWebApplicationTests {


    @Resource
    StudentMapper studentMapper;

    @Resource
    RoleMapper roleMapper;

    @Resource
    NoticeService noticeService;

    @Resource
    FileService fileService;
    @Test
    void contextLoads() {
//        System.out.println(studentMapper.selectList(null));
//        fileService.deleteFile("1724111400_王涛_test.jpg",8);
        fileService.getZipByWId(1);
    }

}
