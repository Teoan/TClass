package com.teoan.tclass.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.user.mapper.DepartmentMapper;
import com.teoan.tclass.user.entity.Department;
import com.teoan.tclass.user.service.DepartmentService;
import org.springframework.stereotype.Service;

/**
 * (Department)表服务实现类
 *
 * @author Teoan
 * @since 2021-05-19 16:48:29
 */
@Service("departmentService")
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

}
