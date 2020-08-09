package com.teoan.tclass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.entity.Notice;
import com.teoan.tclass.entity.Student;
import com.teoan.tclass.mapper.NoticeMapper;
import com.teoan.tclass.service.NoticeService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * (Notice)表服务实现类
 *
 * @author Teoan
 * @since 2020-07-25 10:13:41
 */
@Service("noticeService")
@CacheConfig(cacheNames ="notice_cache")
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Cacheable
    @Override
    public IPage selectNoticesByPage(Long current, Long size, Notice notice) {
        Page<Notice> noticePage = new Page(current,size);
        QueryWrapper<Notice> wrapper = new QueryWrapper<>(notice);
        return getBaseMapper().selectPage(noticePage, wrapper);
    }

    @Override
    public Notice getCurrentNotice() {
        QueryWrapper<Notice> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        wrapper.last("limit 1");
        return getBaseMapper().selectOne(wrapper);
    }

    @CacheEvict
    @Override
    public boolean save(Notice entity) {
        return super.save(entity);
    }

    @CacheEvict(allEntries = true)
    @Override
    public boolean updateById(Notice entity) {
        return super.updateById(entity);
    }

    @CacheEvict(allEntries = true)
    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

}