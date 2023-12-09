package com.teoan.tclass.work.service.impl;

import com.teoan.tclass.common.service.FdfsService;
import com.teoan.tclass.work.service.FileService;
import com.teoan.tclass.work.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Teoan
 * @date 2021/5/19 17:59
 */
@Service("FileService")
public class FileServiceImpl implements FileService {

    @Autowired
    FdfsService fdfsService;

    @Override
    public String saveFile(MultipartFile file) throws IOException {
        String extensionName = FileUtils.getExtensionName(file);
        String uploadFilePath = fdfsService.uploadFile(file.getBytes(), file.getSize(), extensionName);
        return uploadFilePath;
    }

    @Override

    @Caching(evict = {
            @CacheEvict(cacheNames = "getFile_cache",key = "#wId+#fileName"),
            @CacheEvict(cacheNames = "zipFile_cache",key = "#wId")
    })
    public boolean deleteFile(String fileName, Integer wId,String filePath) {
        fdfsService.deleteFile(filePath);
        return true;
    }


    @Override
    public byte[] getFileByte(String filePath) {
        try {
            return fdfsService.downloadFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public boolean deleteFile(String filePath) {
        fdfsService.deleteFile(filePath);
        return true;
    }

}
