package com.teoan.tclass.user.utils;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Teoan
 * @date 2021/5/19 17:36
 */
public class FileUtils {
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
