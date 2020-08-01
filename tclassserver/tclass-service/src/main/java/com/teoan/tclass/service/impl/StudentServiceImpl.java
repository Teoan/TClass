package com.teoan.tclass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.entity.Student;
import com.teoan.tclass.mapper.StudentMapper;
import com.teoan.tclass.service.StudentService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * (Student)表服务实现类
 *
 * @author Teoan
 * @since 2020-07-25 10:14:33
 */
@Service("studentService")
@CacheConfig(cacheNames = "student_cache")
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService, UserDetailsService {





    @Override
    @Cacheable(unless = "#result.records.size()==0")
    public IPage getStudentsByPage(Long current, Long size, Student student) {
        Page<Student> studentPage = new Page<>(current,size);
        QueryWrapper<Student> wrapper = new QueryWrapper<>(student);
        return getBaseMapper().selectPage(studentPage, wrapper);
    }


    @CacheEvict(allEntries = true)
    @Override
    public Boolean addStudent(Student student) {
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.eq("name",student.getName());
        Student one = getBaseMapper().selectOne(wrapper);
        if (one!=null){
            return false;
        }
        getBaseMapper().insert(student);
        return true;
    }


    @CacheEvict(allEntries = true)
    @Override
    public boolean updateById(Student entity) {
        return super.updateById(entity);
    }

    @CacheEvict(allEntries = true)
    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    //springSecurity
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.eq("id",s);
        Student student = getBaseMapper().selectOne(wrapper);
        if(student==null){
            throw new UsernameNotFoundException("用户名不存在!");
        }
        return student;
    }
}