package com.teoan.tclass.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.teoan.tclass.common.service.FdfsService;
import com.teoan.tclass.work.entity.Upload;
import com.teoan.tclass.work.exception.DirPathIsFileException;
import com.teoan.tclass.work.exception.ExtensionNameNotEqualException;
import com.teoan.tclass.work.exception.FileNotExistsException;
import com.teoan.tclass.work.exception.MarkDirException;
import com.teoan.tclass.work.service.FileService;
import com.teoan.tclass.work.service.UploadService;
import com.teoan.tclass.work.utils.FileUtils;
import com.teoan.tclass.work.utils.ZipUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Teoan
 * @date 2021/5/19 17:59
 */
@Service("FileService")
public class FileServiceImpl implements FileService {

    @Autowired
    FdfsService fdfsService;

    @Autowired
    UploadService uploadService;

    @Override
    public String saveFile(MultipartFile file) throws IOException {
        String extensionName = FileUtils.getExtensionName(file);
        String uploadFilePath = fdfsService.uploadFile(file.getBytes(), file.getSize(), extensionName);
        return uploadFilePath;
    }

    @Override

    @Caching(evict = {
            @CacheEvict(cacheNames = "getFile_cache", key = "#wId+#fileName"),
            @CacheEvict(cacheNames = "zipFile_cache", key = "#wId")
    })
    public boolean deleteFile(String fileName, Integer wId, String filePath) {
        fdfsService.deleteFile(filePath);
        return true;
    }

    @Override
    @Cacheable(cacheNames = "zipFile_cache", key = "#wId", unless = "#result==null")
    public File getZipByWId(Integer wId) {
        try {
            String zipDirPath = ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX).getPath();
            File zipDir = new File(zipDirPath + File.separator + "Zip");
            if (!zipDir.exists()) {
                if (!zipDir.mkdir()) {
                    return null;
                }
            }
            File zipFile = new File(zipDir.getAbsolutePath() + File.separator + wId + ".zip");
            QueryWrapper<Upload> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("w_id", wId);
            List<Upload> uploadList = uploadService.list(queryWrapper);
            uploadList = uploadList.stream().map(upload -> {
                try {
                    upload.setFileByte(fdfsService.downloadFile(upload.getFilePath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return upload;
            }).collect(Collectors.toList());
            if (ObjectUtils.isNotEmpty(uploadList)) {
                ZipUtils.zipFiles(zipFile, uploadList);
            } else {
                return null;
            }
            return zipFile;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
//    @Cacheable(cacheNames = "getFile_cache",key = "#wId+#fileName")
    public byte[] getFile(Integer wId, String fileName) {
        QueryWrapper<Upload> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("w_id", wId);
        queryWrapper.eq("file_name", fileName);
        Upload upload = uploadService.getOne(queryWrapper);
        try {
            return fdfsService.downloadFile(upload.getFilePath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public boolean deleteFilesByWId(Integer wId) {
        List<Upload> uploadList = uploadService.getUploadListByWId(wId);
        for (Upload upload : uploadList) {
            fdfsService.deleteFile(upload.getFilePath());
        }
        return true;
    }

}
