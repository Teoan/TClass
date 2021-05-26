package com.teoan.tclass.oauth.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.teoan.tclass.common.sys.entity.Role;
import com.teoan.tclass.common.sys.entity.Student;
import com.teoan.tclass.common.sys.service.RoleService;
import com.teoan.tclass.common.sys.service.StudentService;
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
    StudentService studentService;

    @Autowired
    RoleService roleService;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        
//        由于使用学号进行登录，所以这里的用户名实际上为学号
        Student student = studentService.getById(s);
        if(ObjectUtils.isEmpty(student)){
            throw new UsernameNotFoundException("用户名不存在!");
        }

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        Role role = roleService.getById(student.getRoleId());
        authorityList.add(new SimpleGrantedAuthority(role.getName()));
        // 可用性
        boolean enabled = true;
        // 过期性
        boolean accountNonExpired = true;
        // 有效性
        boolean credentialsNonExpired = true;
        // 锁定性
        boolean accountNonLocked = true;

        return new User(student.getId().toString(),student.getPassword(),enabled,accountNonExpired,credentialsNonExpired,accountNonLocked,authorityList);
    }
}
