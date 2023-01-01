package com.teoan.tclass.notice.controller;

import com.teoan.tclass.common.annotation.TclassLog;
import com.teoan.tclass.common.enums.LogSeverity;
import com.teoan.tclass.notice.dto.NoticeDTO;
import com.teoan.tclass.notice.service.NoticeFeignClient;
import com.teoan.tclass.notice.service.NoticeService;
import org.springframework.web.bind.annotation.RestController;
import com.teoan.tclass.common.result.R;

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
    @TclassLog(operSource = "notice", operName = "查询", severity = LogSeverity.INFO, description = "分页查询所有数据")
    public R selectNoticeByPage(Long current, Long size, NoticeDTO notice) {
        return R.ok(noticeService.selectNoticesByPage(current, size, notice));
    }

    @Override
    @TclassLog(operSource = "notice", operName = "查询", severity = LogSeverity.INFO, description = "通过主键查询单条数据")
    public R selectOne(Serializable id) {
        return R.ok(noticeService.getById(id));
    }

    @Override
    @TclassLog(operSource = "notice", operName = "查询", severity = LogSeverity.INFO, description = "获取最新的通知")
    public R getCurrentNotice() {
        return R.ok(noticeService.getCurrentNotice());
    }

    @Override
    @TclassLog(operSource = "notice", operName = "新增", severity = LogSeverity.INFO, description = "新增数据")
    public R insert(NoticeDTO notice) {
        return R.ok(noticeService.save(notice));
    }

    @Override
    @TclassLog(operSource = "notice", operName = "查询", severity = LogSeverity.INFO, description = "修改数据")
    public R update(NoticeDTO notice) {
        return R.ok(noticeService.updateById(notice));
    }

    @Override
    @TclassLog(operSource = "notice", operName = "删除", severity = LogSeverity.INFO, description = "删除数据")
    public R delete(List<Long> idList) {
        return R.ok(noticeService.removeByIds(idList));
    }
}
