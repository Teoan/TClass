package com.teoan.tclass.user.service.impl;

import com.teoan.tclass.user.exception.ExtensionNameNotEqualException;
import com.teoan.tclass.user.exception.MarkDirException;
import com.teoan.tclass.user.service.FileService;
import com.teoan.tclass.user.utils.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;

/**
 * @author Teoan
 * @date 2021/5/19 17:59
 */
@Service("FileService")
public class FileServiceImpl implements FileService {
    @Value("${file.upload.url}")
    private String path;

    @Override
    public boolean updateUserAvatarFile(MultipartFile file, Integer sId) {
        File avatarDir = new File(path+File.separator+"avatar");
        if(!avatarDir.exists()){
            if(!avatarDir.mkdirs()){
                throw new MarkDirException(HttpStatus.INTERNAL_SERVER_ERROR,"文件目录创建失败！");
            }
        }
        String extensionName = FileUtils.getExtensionName(file).toLowerCase();
        if(!extensionName.equals(".jpg")){
            throw new ExtensionNameNotEqualException(HttpStatus.INTERNAL_SERVER_ERROR,"头像扩展名不符合要求！");
        }
        File avatarFile = new File(path+File.separator+"avatar"+File.separator+sId+".jpg");
        try {
            file.transferTo(avatarFile);
        }catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
        return avatarFile.exists();
    }

    @Override
    public File getUserAvatarFile(String photoPath) {
        File avatarFile = new File(path+File.separator+"avatar"+File.separator+photoPath);
        if (avatarFile.exists()){
            return avatarFile;
        }
        return null;
    }
}
