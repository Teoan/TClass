package com.teoan.tclass.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.entity.Extension;
import com.teoan.tclass.exception.ExtensionIsExists;
import com.teoan.tclass.mapper.ExtensionMapper;
import com.teoan.tclass.service.ExtensionService;
import com.teoan.tclass.service.WorkService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Extension)表服务实现类
 *
 * @author Teoan
 * @since 2020-11-02 21:38:35
 */
@Service("extensionService")
@CacheConfig(cacheNames = "extension_cache")
public class ExtensionServiceImpl extends ServiceImpl<ExtensionMapper, Extension> implements ExtensionService {


    @Resource
    WorkService workService;


    @Cacheable
    @Override
    public List<Extension> list() {
        return super.list();
    }

    @CacheEvict(allEntries = true)
    @Override
    public boolean removeById(Serializable id) {
        if(workService.setDefaultWorkExtensionByEId((Integer)id))
            return super.removeById(id);
        return false;
    }

    @CacheEvict(allEntries = true)
    @Override
    public boolean save(Extension entity) {
        QueryWrapper<Extension> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",entity.getName());
        if(getBaseMapper().selectOne(queryWrapper)!=null){
            throw new ExtensionIsExists(HttpStatus.INTERNAL_SERVER_ERROR,"扩展名已存在,无法添加！");
        }
        return super.save(entity);
    }
}