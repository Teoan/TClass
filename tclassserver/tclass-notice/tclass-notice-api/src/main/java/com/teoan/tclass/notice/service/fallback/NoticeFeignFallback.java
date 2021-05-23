package com.teoan.tclass.notice.service.fallback;

import com.teoan.tclass.notice.dto.NoticeDTO;
import com.teoan.tclass.notice.service.NoticeFeignClient;
import org.springframework.stereotype.Component;
import com.teoan.tclass.common.result.R;
import java.io.Serializable;
import java.util.List;

/**
 * @author Teoan
 * @date 2021/5/20 15:46
 */
@Component
public class NoticeFeignFallback implements NoticeFeignClient {
    @Override
    public R selectNoticeByPage(Long current, Long size, NoticeDTO notice) {
        return R.failed("查询失败");
    }

    @Override
    public R selectOne(Serializable id) {
        return R.failed("获取失败");
    }

    @Override
    public R getCurrentNotice() {
        return  R.failed("获取失败");
    }

    @Override
    public R insert(NoticeDTO notice) {
        return  R.failed("插入失败");
    }

    @Override
    public R update(NoticeDTO notice) {
        return  R.failed("更新失败");
    }

    @Override
    public R delete(List<Long> idList) {
        return  R.failed("删除失败");
    }
}
