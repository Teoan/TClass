package com.teoan.tclass.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teoan.tclass.work.exception.DeleteFileException;
import com.teoan.tclass.work.mapper.WorkMapper;
import com.teoan.tclass.work.entity.Work;
import com.teoan.tclass.work.service.FileService;
import com.teoan.tclass.work.service.UploadService;
import com.teoan.tclass.work.service.WorkService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;

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
    public boolean removeByIds(Collection<? extends Serializable> idList) {

        for (Serializable i : idList) {

            if (fileService.deleteFilesByWId((Integer) i)&&uploadService.deleteUploadByWId((Integer) i)) {
                getBaseMapper().deleteById(i);
            }else{
                throw new DeleteFileException(HttpStatus.INTERNAL_SERVER_ERROR,"删除作业失败，请联系管理员");
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
}
