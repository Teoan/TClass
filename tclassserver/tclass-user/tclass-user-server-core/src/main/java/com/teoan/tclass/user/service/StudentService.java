package com.teoan.tclass.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.teoan.tclass.user.entity.Student;

import java.util.List;

/**
 * (Student)表服务接口
 *
 * @author Teoan
 * @since 2021-05-19 16:49:39
 */
public interface StudentService extends IService<Student> {
    /**
     * 分页并高级查询数据
     *
     * @param current 分页对象
     * @param size    查询数据量
     * @return 所有数据
     */
    IPage getStudentsByPage(Long current, Long size, Student student);

    /**
     * 添加学生用户
     */
    Boolean addStudent(Student student);

    /**
     * 通过id列表获取学生对象
     */
    List<Student> getStudentByIds(List<Long> idList);


    boolean reSetPasswordBySId(Long id);
}
