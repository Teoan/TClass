package com.teoan.tclass.work.utils;

import com.teoan.tclass.common.entity.SysUser;
import com.teoan.tclass.work.entity.Work;
import com.teoan.tclass.work.exception.WorkOrStudentNullException;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Teoan
 * @date 2021/5/19 17:36
 */
public class FileUtils {
    /**
     * 获取文件名字（带扩展名）
     *
     * @param work    作业对象
     * @param sysUser 学生用户对象
     * @param file    上传文件
     * @return 文件名
     */
    public static String getFileSpecificationName(Work work, SysUser sysUser, MultipartFile file) {
        if (sysUser != null && work != null) {
            switch (work.getFileNameFormatEnum()) {
                case 1: {
                    return file.getOriginalFilename();
                }
                case 2: {
                    return sysUser.getId() + "_" + sysUser.getName() + "_" + work.getName() + '.' + work.getExtensionName();
                }
                case 3: {
                    return sysUser.getName() + "_" + sysUser.getId() + "_" + work.getName() + '.' + work.getExtensionName();
                }
                case 4: {
                    return sysUser.getName() + "_" + work.getName() + '.' + work.getExtensionName();
                }
                case 5: {
                    return sysUser.getId() + "_" + work.getName() + '.' + work.getExtensionName();
                }
            }
        }
        return null;
    }


    /**
     * 获取文件扩展名
     *
     * @param file 文件对象
     * @return 文件扩展名
     */
    public static String getExtensionName(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String extensionName = "";
        if (originalFilename != null) {
            int i = originalFilename.lastIndexOf(".");
            if (i != -1) {
                extensionName = file.getOriginalFilename().substring(i + 1);
            }
        }
        return extensionName;

    }
}
