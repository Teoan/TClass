package com.teoan.tclass.user.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author zhuangjy
 * @description
 * @date 2021/5/19 17:58
 */
public interface FileService {

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
