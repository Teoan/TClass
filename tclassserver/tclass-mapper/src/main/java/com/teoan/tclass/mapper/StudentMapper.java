package com.teoan.tclass.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.teoan.tclass.entity.StuDepRef;
import com.teoan.tclass.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Student)表数据库访问层
 *
 * @author Teoan
 * @since 2020-07-25 10:00:24
 */
public interface StudentMapper extends BaseMapper<Student> {

    int updateDepartment(@Param("stuDepRefList") List<StuDepRef> stuDepRefList);


    int deleteDepartmentBySid(Integer sId);

}