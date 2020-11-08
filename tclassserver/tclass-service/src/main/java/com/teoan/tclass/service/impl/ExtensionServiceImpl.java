package com.teoan.tclass.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.entity.Extension;
import com.teoan.tclass.mapper.ExtensionMapper;
import com.teoan.tclass.service.ExtensionService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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


    @Cacheable
    @Override
    public List<Extension> list() {
        return super.list();
    }
}