package com.teoan.tclass.oauth.service;

import com.teoan.tclass.common.entity.SysRole;
import com.teoan.tclass.common.entity.SysUser;
import com.teoan.tclass.common.service.SysRoleService;
import com.teoan.tclass.common.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Teoan
 * @description UserDetail实现类
 * @date 2021/5/26 9:50
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysRoleService sysRoleService;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

//        由于使用学号进行登录，所以这里的用户名实际上为学号
        SysUser sysUser = sysUserService.getById(s);
        if (ObjectUtils.isEmpty(sysUser)) {
            throw new UsernameNotFoundException("用户名不存在!");
        }

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        SysRole sysRole = sysRoleService.getById(sysUser.getRoleId());
        authorityList.add(new SimpleGrantedAuthority(sysRole.getName()));
        // 可用性
        boolean enabled = true;
        // 过期性
        boolean accountNonExpired = true;
        // 有效性
        boolean credentialsNonExpired = true;
        // 锁定性
        boolean accountNonLocked = true;

        return new User(sysUser.getId().toString(), sysUser.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorityList);
    }
}
