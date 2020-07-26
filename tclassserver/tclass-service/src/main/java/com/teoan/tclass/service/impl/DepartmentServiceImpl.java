package com.teoan.tclass.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.mapper.DepartmentMapper;
import com.teoan.tclass.entity.Department;
import com.teoan.tclass.service.DepartmentService;
import org.springframework.stereotype.Service;

/**
 * (Department)表服务实现类
 *
 * @author Teoan
 * @since 2020-07-26 15:40:21
 */
@Service("departmentService")
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

}