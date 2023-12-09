package com.teoan.tclass.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.work.entity.Upload;
import com.teoan.tclass.work.entity.Work;
import com.teoan.tclass.work.mapper.WorkMapper;
import com.teoan.tclass.work.service.FileService;
import com.teoan.tclass.work.service.UploadService;
import com.teoan.tclass.work.service.WorkService;
import com.teoan.tclass.work.utils.ZipUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (Work)表服务实现类
 *
 * @author Teoan
 * @since 2021-05-19 17:28:19
 */
@Service("workService")
@CacheConfig(cacheNames = "work_cache")
public class WorkServiceImpl extends ServiceImpl<WorkMapper, Work> implements WorkService {
    @Resource
    FileService fileService;

    @Resource
    UploadService uploadService;

    @Cacheable
    @Override
    public IPage getWorksByPage(Long current, Long size, Work work) {
        Page<Work> page = new Page<>(current,size);
        QueryWrapper<Work> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        //实现标题模糊查询
        if(work.getName() != null){
            wrapper.like("name",work.getName());
        }
        if(work.getCreateTime()!=null){
            wrapper.like("create_time",new SimpleDateFormat("yyyy-MM-dd").format(work.getCreateTime()));
        }
        IPage<Work> workIPage = getBaseMapper().selectPage(page, wrapper);
        return workIPage;
    }
    @CacheEvict(allEntries = true)
    @Override
    public boolean save(Work entity) {
        return super.save(entity);
    }

    @CacheEvict(allEntries = true)
    @Override
    public boolean updateById(Work entity) {
        return super.updateById(entity);
    }

    @CacheEvict(allEntries = true)
    @Override
    public boolean removeByIds(Collection<?> idList) {

        for (Object i : idList) {

            if (uploadService.deleteUploadByWId((Integer) i)) {
                getBaseMapper().deleteById((Integer) i);
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    @Cacheable
    public Work getById(Serializable id) {
        return super.getById(id);
    }

    @CacheEvict(allEntries = true)
    @Override
    public boolean setDefaultWorkExtensionByEId(Integer EId) {
        UpdateWrapper<Work> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("extension_id",EId);
        return getBaseMapper().update(Work.builder().extensionId(1).build(),updateWrapper)>=0;
    }


    /**
     * 获取作业文件
     *
     * @param wId
     * @param fileName
     */
    @Override
    public byte[] getFile(Integer wId, String fileName) {
        QueryWrapper<Upload> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("w_id", wId);
        queryWrapper.eq("file_name", fileName);
        Upload upload = uploadService.getOne(queryWrapper);
        return fileService.getFileByte(upload.getFilePath());
    }


    /**
     * 根据作业id获取作业文件并打包为zip
     *
     * @param wId 作业id
     */
    @Override
    @Cacheable(cacheNames = "workZipFile_cache", key = "#wId", unless = "#result==null")
    public File getZipByWId(Integer wId) {
        try {
            String zipDirPath = ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX).getPath();
            File zipDir = new File(zipDirPath + File.separator + "Zip");
            if (!zipDir.exists()) {
                if (!zipDir.mkdir()) {
                    return null;
                }
            }
            File zipFile = new File(zipDir.getAbsolutePath() + File.separator + wId + ".zip");
            QueryWrapper<Upload> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("w_id", wId);
            List<Upload> uploadList = uploadService.list(queryWrapper);
            uploadList = uploadList.stream().peek(upload -> {
                upload.setFileByte(fileService.getFileByte(upload.getFilePath()));
            }).collect(Collectors.toList());
            if (ObjectUtils.isNotEmpty(uploadList)) {
                ZipUtils.zipFiles(zipFile, uploadList);
            } else {
                return null;
            }
            return zipFile;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
