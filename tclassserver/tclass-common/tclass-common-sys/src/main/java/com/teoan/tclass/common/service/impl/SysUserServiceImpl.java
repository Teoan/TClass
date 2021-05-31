package com.teoan.tclass.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.common.mapper.SysUserMapper;
import com.teoan.tclass.common.entity.SysUser;
import com.teoan.tclass.common.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * (SysUser)表服务实现类
 *
 * @author Teoan
 * @since 2021-05-19 16:49:40
 */
@Service("sysStudentService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

}
