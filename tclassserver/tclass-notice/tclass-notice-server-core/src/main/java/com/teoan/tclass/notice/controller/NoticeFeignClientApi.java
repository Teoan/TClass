package com.teoan.tclass.notice.controller;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.teoan.tclass.notice.constant.R;
import com.teoan.tclass.notice.dto.NoticeDTO;
import com.teoan.tclass.notice.entity.Notice;
import com.teoan.tclass.notice.service.NoticeFeignClient;
import com.teoan.tclass.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @author Teoan
 * @date 2021/5/18 11:41
 */
@RestController
public class NoticeFeignClientApi implements NoticeFeignClient {
    @Resource
    NoticeService noticeService;


    @Override
    public R selectNoticeByPage(Long current, Long size, NoticeDTO notice) {
        return R.ok(noticeService.selectNoticesByPage(current,size,notice));
    }

    @Override
    public R selectOne(Serializable id) {
        return R.ok(noticeService.getById(id));
    }

    @Override
    public R getCurrentNotice() {
        return R.ok(noticeService.getCurrentNotice());
    }

    @Override
    public R insert(NoticeDTO notice) {
        return R.ok(noticeService.save(notice));
    }

    @Override
    public R update(NoticeDTO notice) {
        return R.ok(noticeService.updateById(notice));
    }

    @Override
    public R delete(List<Long> idList) {
        return R.ok(noticeService.removeByIds(idList));
    }
}
