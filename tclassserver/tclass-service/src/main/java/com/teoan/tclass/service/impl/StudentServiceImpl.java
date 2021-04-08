package com.teoan.tclass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.dto.SelectStudentDTO;
import com.teoan.tclass.entity.StuDepRef;
import com.teoan.tclass.entity.Student;
import com.teoan.tclass.mapper.StudentMapper;
import com.teoan.tclass.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.Serializable;
import java.util.ArrayList;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService,UserDetailsService {


    @Override
    @Cacheable(unless = "#result.records.size()==0")
    public IPage getStudentsByPage(Long current, Long size, SelectStudentDTO selectStudentDTO) {
        Page<Student> studentPage = new Page<>(current,size);
        Student student = new Student();
        BeanUtils.copyProperties(selectStudentDTO,student);
        QueryWrapper<Student> wrapper = new QueryWrapper<>(student);
        //根据名字模糊查询
        if (student.getName()!=null){
            if (!student.getName().isEmpty()) {
                QueryWrapper nameWrapper = new QueryWrapper();
                nameWrapper.like("name", student.getName());
                return getBaseMapper().selectPage(studentPage,nameWrapper);
            }
        }
        return getBaseMapper().selectPage(studentPage, wrapper);
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
        //处理密码更新
        if (password!=null&&!password.isEmpty()){
            entity.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        }
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
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    @CacheEvict(allEntries = true)
    @Override
    public boolean saveBatch(Collection<Student> entityList) {
        return super.saveBatch(entityList);
    }




//    springSecurity
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