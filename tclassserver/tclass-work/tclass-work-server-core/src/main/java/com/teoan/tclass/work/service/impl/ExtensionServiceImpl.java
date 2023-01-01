package com.teoan.tclass.work.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.work.mapper.ExtensionMapper;
import com.teoan.tclass.work.entity.Extension;
import com.teoan.tclass.work.service.ExtensionService;
import org.springframework.stereotype.Service;

/**
 * (Extension)表服务实现类
 *
 * @author Teoan
 * @since 2021-05-19 17:27:18
 */
@Service("extensionService")
public class ExtensionServiceImpl extends ServiceImpl<ExtensionMapper, Extension> implements ExtensionService {

}
