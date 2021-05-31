package com.teoan.tclass.common.until;

import com.teoan.tclass.common.entity.SysUser;
import com.teoan.tclass.common.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * @author Teoan
 * @description 系统工具类
 * @date 2021/5/31 16:22
 */
public class SystemUntil {

    @Autowired
    private static SysUserService sysUserService;

    public static SysUser getCurrentUser(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
       Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof UsernamePasswordAuthenticationToken){
            Object principal = authentication.getPrincipal();
            if(principal instanceof User){
                User user = (User)principal;
                //用户名为用户的id
                SysUser sysUser = sysUserService.getById(user.getUsername());
                return sysUser;
            }
        }
        return null;
    }
}
