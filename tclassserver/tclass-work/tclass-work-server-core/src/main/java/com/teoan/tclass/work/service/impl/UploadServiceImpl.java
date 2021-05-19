package com.teoan.tclass.work.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.work.mapper.UploadMapper;
import com.teoan.tclass.work.entity.Upload;
import com.teoan.tclass.work.service.UploadService;
import org.springframework.stereotype.Service;

/**
 * (Upload)表服务实现类
 *
 * @author Teoan
 * @since 2021-05-19 17:27:50
 */
@Service("uploadService")
public class UploadServiceImpl extends ServiceImpl<UploadMapper, Upload> implements UploadService {

}
