package com.teoan.tclass.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.mapper.PoliticsstatusMapper;
import com.teoan.tclass.entity.Politicsstatus;
import com.teoan.tclass.service.PoliticsstatusService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Politicsstatus)表服务实现类
 *
 * @author Teoan
 * @since 2020-07-26 15:41:22
 */
@Service("politicsstatusService")
@CacheConfig(cacheNames = "politicsstatus_cache")
public class PoliticsstatusServiceImpl extends ServiceImpl<PoliticsstatusMapper, Politicsstatus> implements PoliticsstatusService {
    @Cacheable
    @Override
    public List<Politicsstatus> list() {
        return super.list();
    }
}