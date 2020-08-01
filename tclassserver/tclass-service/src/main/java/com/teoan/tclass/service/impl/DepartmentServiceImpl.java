package com.teoan.tclass.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.mapper.DepartmentMapper;
import com.teoan.tclass.entity.Department;
import com.teoan.tclass.service.DepartmentService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Department)表服务实现类
 *
 * @author Teoan
 * @since 2020-07-26 15:40:21
 */
@Service("departmentService")
@CacheConfig(cacheNames = "department")
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Cacheable
    @Override
    public List<Department> list() {
        return super.list();
    }
}