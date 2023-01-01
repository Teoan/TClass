package com.teoan.tclass.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.common.entity.SysRole;
import com.teoan.tclass.common.mapper.SysRoleMapper;
import com.teoan.tclass.common.service.SysRoleService;
import org.springframework.stereotype.Service;

/**
 * (SysRole)表服务实现类
 *
 * @author Teoan
 * @since 2021-05-19 16:48:56
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

}
