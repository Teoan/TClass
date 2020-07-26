package com.teoan.tclass.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.entity.Upload;
import com.teoan.tclass.mapper.UploadMapper;
import com.teoan.tclass.service.UploadService;
import org.springframework.stereotype.Service;

/**
 * (Upload)表服务实现类
 *
 * @author Teoan
 * @since 2020-07-25 10:14:51
 */
@Service("uploadService")
public class UploadServiceImpl extends ServiceImpl<UploadMapper, Upload> implements UploadService {

}