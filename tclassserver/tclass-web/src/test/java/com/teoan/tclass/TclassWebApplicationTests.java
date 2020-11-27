package com.teoan.tclass;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teoan.tclass.entity.Role;
import com.teoan.tclass.entity.Student;
import com.teoan.tclass.mapper.NoticeMapper;
import com.teoan.tclass.mapper.RoleMapper;
import com.teoan.tclass.mapper.StudentMapper;
import com.teoan.tclass.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
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

    @Resource
    UploadService uploadService;

    @Resource
    WorkService workService;

    @Test
    void contextLoads() {
//        System.out.println(studentMapper.selectList(null));
//        fileService.deleteFile("1724111400_王涛_test.jpg",8);
//        System.out.println(uploadService.getUploadPageByWId((long) 1, (long) 10, 8));
//        System.out.println(fileService.deleteFilesByWid(1));

//        List<Integer> longs = new ArrayList<>();
//        longs.add(1);
//        longs.add(2);
//        longs.add(3);
//        longs.add(4);
//        workService.removeByIds(longs);

    }

}
