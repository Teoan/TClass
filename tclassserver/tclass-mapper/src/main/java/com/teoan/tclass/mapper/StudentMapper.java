package com.teoan.tclass.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.teoan.tclass.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (Student)表数据库访问层
 *
 * @author Teoan
 * @since 2020-07-25 10:00:24
 */
public interface StudentMapper extends BaseMapper<Student> {

}