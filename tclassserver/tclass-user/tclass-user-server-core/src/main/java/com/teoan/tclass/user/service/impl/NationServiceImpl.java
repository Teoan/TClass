package com.teoan.tclass.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.user.mapper.NationMapper;
import com.teoan.tclass.user.entity.Nation;
import com.teoan.tclass.user.service.NationService;
import org.springframework.stereotype.Service;

/**
 * (Nation)表服务实现类
 *
 * @author Teoan
 * @since 2021-05-19 16:47:03
 */
@Service("nationService")
public class NationServiceImpl extends ServiceImpl<NationMapper, Nation> implements NationService {

}
