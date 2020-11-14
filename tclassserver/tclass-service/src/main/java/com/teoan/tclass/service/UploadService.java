package com.teoan.tclass.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.teoan.tclass.entity.Upload;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * (Upload)表服务接口
 *
 * @author Teoan
 * @since 2020-07-25 10:14:50
 */
public interface UploadService extends IService<Upload> {

    /**
     * 上传文件
     *
     * @param wId 作业id
     * @param sId 学号
     * @param file 上传的文件
     */
    void uploadFile(Integer wId, Integer sId, MultipartFile file);

    /**
     * 检查用户是否已上传过文件
     * @param wId 作业id
     * @param sId 学号
     * @return 文件信息
     */
    Upload isUploadedWorkFile(Integer wId,Integer sId);


    /**
     * 删除上传的文件
     * @param wId 作业Id
     * @param sId 学号
     * @param fileName 文件名
     */
    boolean deleteUploadFile(Integer wId,Integer sId,String fileName);


}