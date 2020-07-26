package com.teoan.tclass.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.entity.Work;
import com.teoan.tclass.mapper.WorkMapper;
import com.teoan.tclass.service.WorkService;
import org.springframework.stereotype.Service;

/**
 * (Work)表服务实现类
 *
 * @author Teoan
 * @since 2020-07-25 10:14:11
 */
@Service("workService")
public class WorkServiceImpl extends ServiceImpl<WorkMapper, Work> implements WorkService {

}