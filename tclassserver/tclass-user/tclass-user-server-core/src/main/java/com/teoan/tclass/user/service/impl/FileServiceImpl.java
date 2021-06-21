package com.teoan.tclass.user.service.impl;

import com.teoan.tclass.common.entity.SysUser;
import com.teoan.tclass.common.result.R;
import com.teoan.tclass.common.service.AuthUserService;
import com.teoan.tclass.user.entity.Student;
import com.teoan.tclass.common.service.FdfsService;
import com.teoan.tclass.user.service.FileService;
import com.teoan.tclass.user.service.StudentService;
import com.teoan.tclass.user.utils.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author Teoan
 * @date 2021/5/19 17:59
 */
@Service("FileService")
public class FileServiceImpl implements FileService {
    @Autowired
    private FdfsService fdfsService;

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private StudentService studentService;

    @Override
    public R updateUserAvatarFile(MultipartFile avayarFile) {
        String extensionName = FileUtils.getExtensionName(avayarFile).toLowerCase();
        if(!("jpg".equals(extensionName)||"jpeg".equals(extensionName))){
            return R.failed("头像扩展名不符合要求！");
        }
        try {
            //剪切图片 TODO 未测试
            BufferedImage bufferedImage = ImageIO.read(avayarFile.getInputStream());
            int imageWidth = Math.min(bufferedImage.getWidth(), bufferedImage.getHeight());
            bufferedImage = bufferedImage.getSubimage(0,0,imageWidth,imageWidth);
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
            ImageIO.write(bufferedImage,extensionName,imOut);


            String filePath = fdfsService.uploadUserAvatarImageFile(new ByteArrayInputStream(bs.toByteArray()),avayarFile.getSize(), extensionName);
            SysUser currentUser = authUserService.getCurrentUser();
            //删除旧的头像文件
            Student student = studentService.getById(currentUser.getId());
            if(StringUtils.isNotBlank(student.getAvatarUrl())){
                fdfsService.deleteFile(student.getAvatarUrl());
            }
            //更新用户头像信息
            student.setAvatarUrl(filePath);
            studentService.updateById(student);
        } catch (Exception e) {
            R.failed(e.getMessage());
            e.printStackTrace();
        }
        return R.ok(true);
    }

    @Override
    public byte[] getUserAvatarFile(String photoPath) {
        try {
            return fdfsService.downloadFile(photoPath);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
