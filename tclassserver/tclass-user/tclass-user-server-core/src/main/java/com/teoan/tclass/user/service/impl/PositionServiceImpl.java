package com.teoan.tclass.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.user.mapper.PositionMapper;
import com.teoan.tclass.user.entity.Position;
import com.teoan.tclass.user.service.PositionService;
import org.springframework.stereotype.Service;

/**
 * (Position)表服务实现类
 *
 * @author Teoan
 * @since 2021-05-19 16:47:46
 */
@Service("positionService")
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements PositionService {

}
