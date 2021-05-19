package com.teoan.tclass.work.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author zhuangjy
 * @description
 * @date 2021/5/19 17:58
 */
public interface FileService {
    /**
     * 保存文件
     * @param file 文件
     * @param fileName 保存的文件名
     */
    void saveFile(MultipartFile file, String fileName, Integer wId);

    /**
     * 删除文件
     * @param fileName 文件名称
     * @param wId 作业id
     */
    boolean deleteFile(String fileName,Integer wId);

    /**
     * 根据作业id获取作业文件并打包为zip
     * @param wId 作业id
     */
    File getZipByWId(Integer wId);

    /**
     * 获取一个文件
     * @param wId 作业id
     * @param fileName 文件名
     * @return 文件对象
     */
    File getFile(Integer wId, String fileName);

    /**
     * 根据作业id删除所有文件
     * @param wId 作业id
     * @return 是否成功
     */
    boolean deleteFilesByWId(Integer wId);

    /**
     * 更新用户头像
     * @param file 头像文件
     * @param sId 用户id
     * @return 是否成功
     */
    boolean updateUserAvatarFile(MultipartFile file,Integer sId);

    /**
     * 获取用户头像文件
     * @param photoPath 用户id
     * @return 是否成功
     */
    File getUserAvatarFile(String photoPath);
}
