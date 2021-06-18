package com.teoan.tclass.user.service.impl;

import com.teoan.tclass.common.entity.SysUser;
import com.teoan.tclass.common.result.R;
import com.teoan.tclass.common.service.AuthUserService;
import com.teoan.tclass.user.entity.Student;
import com.teoan.tclass.common.service.FdfsService;
import com.teoan.tclass.user.service.FileService;
import com.teoan.tclass.user.service.StudentService;
import com.teoan.tclass.user.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
            String filePath = fdfsService.uploadUserAvatarImageFile(avayarFile.getInputStream(),avayarFile.getSize(), 150, 150, extensionName);
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
