package com.teoan.tclass.common.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.common.sys.entity.Role;
import com.teoan.tclass.common.sys.mapper.RoleMapper;
import com.teoan.tclass.common.sys.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * (Role)表服务实现类
 *
 * @author Teoan
 * @since 2021-05-19 16:48:56
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
