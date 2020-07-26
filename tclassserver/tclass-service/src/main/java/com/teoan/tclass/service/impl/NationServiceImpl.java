package com.teoan.tclass.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.mapper.NationMapper;
import com.teoan.tclass.entity.Nation;
import com.teoan.tclass.service.NationService;
import org.springframework.stereotype.Service;

/**
 * (Nation)表服务实现类
 *
 * @author Teoan
 * @since 2020-07-26 15:40:44
 */
@Service("nationService")
public class NationServiceImpl extends ServiceImpl<NationMapper, Nation> implements NationService {

}