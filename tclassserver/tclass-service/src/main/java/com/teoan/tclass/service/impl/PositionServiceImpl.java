package com.teoan.tclass.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.mapper.PositionMapper;
import com.teoan.tclass.entity.Position;
import com.teoan.tclass.service.PositionService;
import org.springframework.stereotype.Service;

/**
 * (Position)表服务实现类
 *
 * @author Teoan
 * @since 2020-07-26 15:43:03
 */
@Service("positionService")
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements PositionService {

}