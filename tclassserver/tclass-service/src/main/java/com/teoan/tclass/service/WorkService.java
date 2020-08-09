package com.teoan.tclass.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.teoan.tclass.entity.Work;

import java.util.List;

/**
 * (Work)表服务接口
 *
 * @author Teoan
 * @since 2020-07-25 10:14:10
 */
public interface WorkService extends IService<Work> {
    /**
     * 分页并高级查询数据
     *
     * @param current 分页对象
     * @param size 查询数据量
     * @return 所有数据
     */
    IPage getWorksByPage(Long current, Long size, Work work);

    /**
     * 获取最新发布的作业
     *
     * @return 作业
     */

    Work getCurrentWork();
}