package com.teoan.tclass;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teoan.tclass.entity.Role;
import com.teoan.tclass.entity.Student;
import com.teoan.tclass.mapper.RoleMapper;
import com.teoan.tclass.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
class TclassWebApplicationTests {


    @Resource
    StudentMapper studentMapper;

    @Resource
    RoleMapper roleMapper;

    @Test
    void contextLoads() {
        List<Role> roleList = roleMapper.selectList(null);
        System.out.println(roleList.indexOf(Role.builder().zhName("管理员").build()));

    }

}
