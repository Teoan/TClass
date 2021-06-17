package com.teoan.tclass.user.service.impl;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.upload.FastImageFile;
import com.github.tobato.fastdfs.domain.upload.ThumbImage;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.teoan.tclass.common.entity.SysUser;
import com.teoan.tclass.common.result.R;
import com.teoan.tclass.common.service.AuthUserService;
import com.teoan.tclass.user.entity.Student;
import com.teoan.tclass.user.exception.ExtensionNameNotEqualException;
import com.teoan.tclass.user.exception.MarkDirException;
import com.teoan.tclass.user.service.FdfsService;
import com.teoan.tclass.user.service.FileService;
import com.teoan.tclass.user.service.StudentService;
import com.teoan.tclass.user.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
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
        if(!"jpg".equals(extensionName)){
            return R.failed("头像扩展名不符合要求！");
        }
        try {
//            BufferedImage bufferedImage = ImageIO.read(avayarFile.getInputStream());
//            // 剪切图片
//            int imageWidth = Math.min(bufferedImage.getWidth(), bufferedImage.getHeight());
//            bufferedImage = bufferedImage.getSubimage(0,0,imageWidth,imageWidth);
//            // 上传文件
////            String[] remoteInfo;
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            ImageIO.write(bufferedImage,"jpg",outputStream);
////            remoteInfo = fastdfsClientService.autoUpload(outputStream.toByteArray(), "jpg");
////            System.out.println("上传的服务器分组: " + remoteInfo[0]);
////            System.out.println("上传的服务器ID: " + remoteInfo[1]);
            String filePath = fdfsService.uploadUserAvatarImageFile(avayarFile, 150, 150, extensionName);
            SysUser currentUser = authUserService.getCurrentUser();
            //更新用户头像信息
            Student student = Student.builder().id(currentUser.getId()).avatarUrl(filePath).build();
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
