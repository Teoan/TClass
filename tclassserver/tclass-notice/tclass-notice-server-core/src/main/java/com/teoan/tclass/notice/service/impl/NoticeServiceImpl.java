package com.teoan.tclass.notice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.notice.dto.NoticeDTO;
import com.teoan.tclass.notice.entity.Notice;
import com.teoan.tclass.notice.mapper.NoticeMapper;
import com.teoan.tclass.notice.service.NoticeService;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;

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
    public IPage selectNoticesByPage(Long current, Long size, NoticeDTO notice) {

        Page<Notice> noticePage = new Page(current,size);
        QueryWrapper<Notice> wrapper = new QueryWrapper<>();
        //实现标题模糊查询
        if(notice.getTitle()!=null){
            wrapper.like("title",notice.getTitle());
        }
        if(notice.getCreateTime()!=null){
            wrapper.like("create_time",new SimpleDateFormat("yyyy-MM-dd").format(notice.getCreateTime()));
        }
        wrapper.orderByDesc("create_time");
        return getBaseMapper().selectPage(noticePage, wrapper);
    }

    @Cacheable
    @Override
    public Notice getCurrentNotice() {
        QueryWrapper<Notice> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        wrapper.last("limit 1");
        return getBaseMapper().selectOne(wrapper);
    }

    @CacheEvict(allEntries = true)
    @Override
    public boolean save(NoticeDTO noticeDTO) {
        Notice notice = new Notice();
        BeanUtils.copyProperties(noticeDTO,notice);
        return super.save(notice);
    }

    @CacheEvict(allEntries = true)
    @Override
    public boolean updateById(NoticeDTO noticeDTO) {
        Notice notice = new Notice();
        BeanUtils.copyProperties(noticeDTO,notice);
        return super.updateById(notice);
    }

    @CacheEvict(allEntries = true)
    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

}
