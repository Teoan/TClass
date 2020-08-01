package com.teoan.tclass.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.mapper.RoleMapper;
import com.teoan.tclass.entity.Role;
import com.teoan.tclass.service.RoleService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Role)表服务实现类
 *
 * @author Teoan
 * @since 2020-07-26 15:43:18
 */
@Service("roleService")
@CacheConfig(cacheNames = "role_cache")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Cacheable
    @Override
    public List<Role> list() {
        return super.list();
    }
}