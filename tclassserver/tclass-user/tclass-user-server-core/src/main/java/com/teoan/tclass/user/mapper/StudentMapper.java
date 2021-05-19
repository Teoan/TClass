package com.teoan.tclass.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.teoan.tclass.user.entity.StuDepRef;
import com.teoan.tclass.user.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Student)表数据库访问层
 *
 * @author Teoan
 * @since 2021-05-19 16:49:39
 */
public interface StudentMapper extends BaseMapper<Student> {

    int updateDepartment(@Param("stuDepRefList") List<StuDepRef> stuDepRefList);


    int deleteDepartmentBySid(Integer sId);
}