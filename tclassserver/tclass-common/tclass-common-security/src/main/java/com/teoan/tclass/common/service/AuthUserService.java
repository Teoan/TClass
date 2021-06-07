package com.teoan.tclass.common.service;

import com.teoan.tclass.common.entity.SysRole;
import com.teoan.tclass.common.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @author Teoan
 * @description 登录用户服务类，提供用户信息
 * @date 2021/6/3 9:52
 */
@Service("authUserServer")
public class AuthUserService {

    @Autowired
    private  SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;


    //获取当前用户信息
    public SysUser getCurrentUser(){

        //获取到当前登录用户的id
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser sysUser = sysUserService.getById(userId);
        if (ObjectUtils.isEmpty(sysUser)){
            return null;
        }
        return sysUser;
    }
    //获取当前用户权限信息
    public SysRole getCurrentUserRole(){
        //获取到当前登录用户的id
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser sysUser = sysUserService.getById(userId);
        if (ObjectUtils.isEmpty(sysUser)){
            return null;
        }
        return sysRoleService.getById(sysUser.getRoleId());
    }
}
