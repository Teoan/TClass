package com.teoan.tclass.work.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author Teoan
 * @date 2021/5/19 17:58
 */
public interface FileService {
    /**
     * 保存文件
     * @param file 文件
     * @return 返回文件路径
     */
    String saveFile(MultipartFile file) throws IOException;

    /**
     * 删除文件
     * @param fileName 文件名称
     * @param wId 作业id
     * @param filePath 文件路径
     */
    boolean deleteFile(String fileName,Integer wId,String filePath);

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
    byte[] getFile(Integer wId, String fileName);

    /**
     * 根据作业id删除所有文件
     * @param wId 作业id
     * @return 是否成功
     */
    boolean deleteFilesByWId(Integer wId);

}
