package com.teoan.tclass.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.teoan.tclass.entity.Notice;
import com.teoan.tclass.entity.Student;

import java.util.List;

/**
 * (Notice)表服务接口
 *
 * @author Teoan
 * @since 2020-07-25 10:13:40
 */
public interface NoticeService extends IService<Notice> {


    /**
     * 分页并高级查询数据
     *
     * @param current 分页对象
     * @param size 查询数据量
     * @return 所有数据
     */
    IPage selectNoticesByPage(Long current, Long size, Notice notice);
}