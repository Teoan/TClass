package com.teoan.tclass.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.common.entity.SysUser;
import com.teoan.tclass.common.service.SysUserService;
import com.teoan.tclass.work.entity.Work;
import com.teoan.tclass.work.mapper.UploadMapper;
import com.teoan.tclass.work.entity.Upload;
import com.teoan.tclass.work.mapper.WorkMapper;
import com.teoan.tclass.work.service.FileService;
import com.teoan.tclass.work.service.UploadService;
import com.teoan.tclass.work.utils.FileUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * (Upload)表服务实现类
 *
 * @author Teoan
 * @since 2021-05-19 17:27:50
 */
@Service("uploadService")
public class UploadServiceImpl extends ServiceImpl<UploadMapper, Upload> implements UploadService {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private WorkMapper workMapper;

    @Resource
    private FileService fileService;

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = "work_cache",allEntries = true),
            @CacheEvict(cacheNames = "upload_cache",allEntries = true),
            @CacheEvict(cacheNames = "zipFile_cache",key = "#wId")
    })
    public boolean uploadFile(Integer wId, Integer sId, MultipartFile file) {
        //检查文件扩展名
        SysUser sysUser = sysUserService.getById(sId);
        Work work = workMapper.selectById(wId);

        if(!work.getExtensionName().equals("无")){
            String extensionName = FileUtils.getExtensionName(file).toLowerCase();
            if(!extensionName.equals(work.getExtensionName())){
                return false;
            }
        }
        //保存文件
        String fileName = FileUtils.getFileSpecificationName(work,sysUser,file);
        if(StringUtils.isBlank(fileName)){
            return false;
        }
        try {
            String filePath = fileService.saveFile(file);
            Upload upload = Upload.builder().sId(sId).wId(wId).fileType(file.getContentType())
                    .fileName(fileName).size(file.getSize()).filePath(filePath).build();
            getBaseMapper().insert(upload);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }



    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = "work_cache",allEntries = true),
            @CacheEvict(cacheNames = "upload_cache",allEntries = true),
            @CacheEvict(cacheNames = "isUploadedWorkFile",key = "T(String).valueOf(#upload.getWId()).concat('-').concat(#upload.getSId())")
    })
    public boolean deleteUploadFile(Upload upload) {
        if(fileService.deleteFile(upload.getFileName(),upload.getWId(),upload.getFilePath())){
            QueryWrapper<Upload> wrapper = new QueryWrapper<>();
            wrapper.eq("s_id",upload.getSId());
            wrapper.eq("w_id",upload.getWId());
            getBaseMapper().delete(wrapper);
            return true;
        }
        return false;
    }

    @Override
    @Cacheable(cacheNames = "isUploadedWorkFile",key = "T(String).valueOf(#wId).concat('-').concat(#sId)",unless="#result == null")
    public Upload isUploadedWorkFile(Integer wId, Integer sId) {
        QueryWrapper<Upload> wrapper = new QueryWrapper<>();
        wrapper.eq("s_id",sId).eq("w_id",wId);
        return getBaseMapper().selectOne(wrapper);
    }

    @Override
    @Cacheable(cacheNames = "upload_cache")
    public IPage<Upload> getUploadPageByWId(Long current, Long size, Integer wId) {
        QueryWrapper<Upload> wrapper = new QueryWrapper<>();
        Page<Upload> page = new Page<>(current,size);
        wrapper.eq("w_id",wId);
        return getBaseMapper().selectPage(page,wrapper);
    }

    @Override
    public boolean deleteUploadByWId(Integer wId) {
        QueryWrapper<Upload> wrapper = new QueryWrapper<>();
        wrapper.eq("w_id",wId);
        return getBaseMapper().delete(wrapper)>=0;
    }
}
