package com.teoan.tclass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    public void uploadFile(Integer wId, Integer sId, MultipartFile file) {

        //检查文件扩展名
        Student student = studentMapper.selectById(sId);
        Work work = workMapper.selectById(wId);
        String extensionName = FileUtils.getExtensionName(file);
        if(!extensionName.equals(work.getExtensionName())){
            throw new ExtensionNameNotEqualException(HttpStatus.INTERNAL_SERVER_ERROR,"文件扩展名不符合要求！");
        }


        //保存文件
        String fileName = FileUtils.getFileSpecificationName(work,student,file);
        Upload upload = Upload.builder().sId(sId).wId(wId).fileType(file.getContentType()).fileName(fileName).size(file.getSize()).build();
        getBaseMapper().insert(upload);
        fileService.saveFile(file,fileName,wId);
    }

    @Override
    public boolean deleteUploadFile(Integer wId, Integer sId, String fileName) {

        QueryWrapper<Upload> wrapper = new QueryWrapper<>();
        wrapper.eq("s_id",sId).eq("w_id",wId);
        getBaseMapper().delete(wrapper);
        return fileService.deleteFile(fileName,wId);
    }

    @Override
    public Upload isUploadedWorkFile(Integer wId, Integer sId) {
        QueryWrapper<Upload> wrapper = new QueryWrapper<>();
        wrapper.eq("s_id",sId).eq("w_id",wId);
        return getBaseMapper().selectOne(wrapper);
    }
}