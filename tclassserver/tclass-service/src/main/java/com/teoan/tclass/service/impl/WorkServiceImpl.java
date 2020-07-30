package com.teoan.tclass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.entity.Work;
import com.teoan.tclass.mapper.WorkMapper;
import com.teoan.tclass.service.WorkService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Work)表服务实现类
 *
 * @author Teoan
 * @since 2020-07-25 10:14:11
 */
@Service("workService")
public class WorkServiceImpl extends ServiceImpl<WorkMapper, Work> implements WorkService {
    @Override
    public IPage getWorksByPage(Long current, Long size, Work work) {
        Page<Work> page = new Page<>(current,size);
        QueryWrapper<Work> wrapper = new QueryWrapper<>(work);
        IPage<Work> workIPage = getBaseMapper().selectPage(page, wrapper);
        return workIPage;
    }
}