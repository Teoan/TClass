package com.teoan.tclass.user.service.impl;

import com.teoan.tclass.common.result.R;
import com.teoan.tclass.user.exception.ExtensionNameNotEqualException;
import com.teoan.tclass.user.exception.MarkDirException;
import com.teoan.tclass.user.service.FileService;
import com.teoan.tclass.user.utils.FileUtils;
import io.github.bluemiaomiao.service.FastdfsClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Teoan
 * @date 2021/5/19 17:59
 */
@Service("FileService")
public class FileServiceImpl implements FileService {
    @Value("${file.upload.url}")
    private String path;

    @Autowired
    private FastdfsClientService fastdfsClientService;


    @Override
    public R updateUserAvatarFile(MultipartFile avayarFile) {
        String extensionName = FileUtils.getExtensionName(avayarFile).toLowerCase();
        if(!".jpg".equals(extensionName)){
            return R.failed("头像扩展名不符合要求！");
        }
        try {
            BufferedImage bufferedImage = ImageIO.read(avayarFile.getInputStream());
            // 剪切图片
            int imageWidth = Math.min(bufferedImage.getWidth(), bufferedImage.getHeight());
            bufferedImage = bufferedImage.getSubimage(0,0,imageWidth,imageWidth);
            // 上传文件
//            String[] remoteInfo;
//            remoteInfo = fastdfsClientService.autoUpload(bufferedImage.get, ".jpg");




        } catch (Exception e) {
            R.failed(e.getMessage());
            e.printStackTrace();
        }




        return R.ok(true);
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
