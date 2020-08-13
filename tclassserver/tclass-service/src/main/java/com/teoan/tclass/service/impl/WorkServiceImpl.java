package com.teoan.tclass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.entity.Notice;
import com.teoan.tclass.entity.Work;
import com.teoan.tclass.mapper.WorkMapper;
import com.teoan.tclass.service.WorkService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * (Work)表服务实现类
 *
 * @author Teoan
 * @since 2020-07-25 10:14:11
 */
@Service("workService")
@CacheConfig(cacheNames = "work_cache")
public class WorkServiceImpl extends ServiceImpl<WorkMapper, Work> implements WorkService {

    @Cacheable
    @Override
    public IPage getWorksByPage(Long current, Long size, Work work) {
        Page<Work> page = new Page<>(current,size);
        QueryWrapper<Work> wrapper = new QueryWrapper<>(work);
        IPage<Work> workIPage = getBaseMapper().selectPage(page, wrapper);
        return workIPage;
    }

    @Cacheable
    @Override
    public Work getCurrentWork() {
        QueryWrapper<Work> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        wrapper.last("limit 1");
        return getBaseMapper().selectOne(wrapper);
    }

    @CacheEvict
    @Override
    public boolean save(Work entity) {
        return super.save(entity);
    }

    @CacheEvict(allEntries = true)
    @Override
    public boolean updateById(Work entity) {
        return super.updateById(entity);
    }

    @CacheEvict(allEntries = true)
    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

}