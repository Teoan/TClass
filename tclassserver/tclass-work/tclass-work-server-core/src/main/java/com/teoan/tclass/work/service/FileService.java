package com.teoan.tclass.work.service;

import org.springframework.web.multipart.MultipartFile;

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
     * 获取一个文件byte
     * @return 文件对象
     */
    byte[] getFileByte(String filePath);

    /**
     * 根据路径删除文件
     */
    boolean deleteFile(String filePath);

}
