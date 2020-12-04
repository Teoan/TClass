package com.teoan.tclass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.entity.Student;
import com.teoan.tclass.entity.Upload;
import com.teoan.tclass.entity.Work;
import com.teoan.tclass.exception.ExtensionNameNotEqualException;
import com.teoan.tclass.mapper.StudentMapper;
import com.teoan.tclass.mapper.UploadMapper;
import com.teoan.tclass.mapper.WorkMapper;
import com.teoan.tclass.service.FileService;
import com.teoan.tclass.service.UploadService;
import com.teoan.tclass.utils.FileUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * (Upload)表服务实现类
 *
 * @author Teoan
 * @since 2020-07-25 10:14:51
 */
@Service("uploadService")
public class UploadServiceImpl extends ServiceImpl<UploadMapper, Upload> implements UploadService {

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private WorkMapper workMapper;

    @Resource
    private FileService fileService;

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = "work_cache",allEntries = true),
            @CacheEvict(cacheNames = "upload_cache",allEntries = true)
    })
    public void uploadFile(Integer wId, Integer sId, MultipartFile file) {

        //检查文件扩展名
        Student student = studentMapper.selectById(sId);
        Work work = workMapper.selectById(wId);

        if(!work.getExtensionName().equals("无")){
            String extensionName = FileUtils.getExtensionName(file).toLowerCase();
            if(!extensionName.equals(work.getExtensionName())){
                throw new ExtensionNameNotEqualException(HttpStatus.INTERNAL_SERVER_ERROR,"文件扩展名不符合要求！");
            }
        }
        //保存文件
        String fileName = FileUtils.getFileSpecificationName(work,student,file);
        fileService.saveFile(file,fileName,wId);
        Upload upload = Upload.builder().sId(sId).wId(wId).fileType(file.getContentType()).fileName(fileName).size(file.getSize()).build();
        getBaseMapper().insert(upload);
    }


    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = "work_cache",allEntries = true),
            @CacheEvict(cacheNames = "upload_cache",allEntries = true),
            @CacheEvict(cacheNames = "isUploadedWorkFile",key = "T(String).valueOf(#wId).concat('-').concat(#sId)")
    })
    public boolean deleteUploadFile(Integer wId, Integer sId, String fileName) {
        if(fileService.deleteFile(fileName,wId)){
            QueryWrapper<Upload> wrapper = new QueryWrapper<>();
            wrapper.eq("s_id",sId).eq("w_id",wId);
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