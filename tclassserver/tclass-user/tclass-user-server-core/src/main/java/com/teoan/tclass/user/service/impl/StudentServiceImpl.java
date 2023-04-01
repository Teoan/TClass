package com.teoan.tclass.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.user.dto.StudentPageDTO;
import com.teoan.tclass.user.entity.StuDepRef;
import com.teoan.tclass.user.entity.Student;
import com.teoan.tclass.user.mapper.StudentMapper;
import com.teoan.tclass.user.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (Student)表服务实现类
 *
 * @author Teoan
 * @since 2021-05-19 16:49:40
 */
@Service("studentService")
@CacheConfig(cacheNames = "student_cache")
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Override
    @Cacheable(unless = "#result.records.size()==0||#result==null")
    public IPage getStudentsByPage(Long current, Long size, Student student) {
        Page<Student> studentPage = new Page<>(current,size);
        QueryWrapper<Student> wrapper = new QueryWrapper<>(student);
        //根据名字模糊查询
        if (student.getName()!=null){
            if (!student.getName().isEmpty()) {
                QueryWrapper nameWrapper = new QueryWrapper();
                nameWrapper.like("name", student.getName());
                studentPage = getBaseMapper().selectPage(studentPage,nameWrapper);
            }
        }
        studentPage = getBaseMapper().selectPage(studentPage, wrapper);
        List<Student> studentList = studentPage.getRecords();
        List<StudentPageDTO> studentPageDTOS = studentList.stream().map(student1 -> {
            StudentPageDTO studentPageDTO = new StudentPageDTO();
            BeanUtils.copyProperties(student1,studentPageDTO);
            return studentPageDTO;
        }).collect(Collectors.toList());
        Page<StudentPageDTO> studentDTOPage = new Page<>(current,size);
        studentDTOPage.setRecords(studentPageDTOS);
        return studentDTOPage;
    }


    @Cacheable(cacheNames = "getStudentById",key = "#id")
    @Override
    public Student getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    @Cacheable
    public List<Student> getStudentByIds(List<Long> idList){
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.in("id",idList);
        return  getBaseMapper().selectList(wrapper);
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

    @Caching(evict = {
            @CacheEvict(cacheNames = "student_cache",allEntries = true),
            @CacheEvict(cacheNames = "getStudentById",key = "#entity.id")
    })
    @Override
    public boolean updateById(Student entity) {
        String password = entity.getPassword();
        List<Integer> departmentIdList = entity.getDepartmentIdList();
        //处理学生与部门映射
        if(departmentIdList!=null&&departmentIdList.size()>0){
            List<StuDepRef> stuDepRefList = new ArrayList<>();
            departmentIdList.forEach(item->{
                stuDepRefList.add(new StuDepRef(entity.getId(),item));
            });
            getBaseMapper().deleteDepartmentBySid(entity.getId());
            return (getBaseMapper().updateDepartment(stuDepRefList)>0&&getBaseMapper().updateById(entity)>0);
        }

        return super.updateById(entity);
    }

    @CacheEvict(allEntries = true)
    @Override
    public boolean removeByIds(Collection<?> idList) {
        return super.removeByIds(idList);
    }

    @CacheEvict(allEntries = true)
    @Override
    public boolean saveBatch(Collection<Student> entityList) {
        return super.saveBatch(entityList);
    }


    @Override
    public boolean reSetPasswordBySId(Long id) {
        Student student = getBaseMapper().selectById(id);
        //处理密码更新
        student.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        if(getBaseMapper().updateById(student)>0){
            return true;
        }
        return false;
    }
}
