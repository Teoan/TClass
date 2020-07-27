package com.teoan.tclass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.entity.Notice;
import com.teoan.tclass.mapper.NoticeMapper;
import com.teoan.tclass.service.NoticeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Notice)表服务实现类
 *
 * @author Teoan
 * @since 2020-07-25 10:13:41
 */
@Service("noticeService")
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
    @Override
    public List<Notice> selectNoticesByPage(Long current, Long size, Notice notice) {
        Page<Notice> noticePage = new Page(current,size);
        QueryWrapper<Notice> wrapper = new QueryWrapper<>(notice);
        List<Notice> noticeList = getBaseMapper().selectPage(noticePage, wrapper).getRecords();
        return noticeList;
    }
}