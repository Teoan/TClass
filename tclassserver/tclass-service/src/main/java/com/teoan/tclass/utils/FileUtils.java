package com.teoan.tclass.utils;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.teoan.tclass.entity.Student;
import com.teoan.tclass.entity.Work;
import com.teoan.tclass.exception.WorkOrStudentNullException;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Teoan
 * @description
 * @date 2020/11/8 22:04
 */
public class FileUtils {


    /**
     * 获取文件名字（带扩展名）
     * @param work 作业对象
     * @param student 学生用户对象
     * @param file 上传文件
     * @return 文件名
     */
    public static String getFileSpecificationName(Work work,Student student,MultipartFile file){
        if(student!=null&&work!=null){
            switch (work.getFileNameFormatEnum()){
                case 1:{return file.getOriginalFilename();
                }
                case 2:{return student.getId()+"_"+student.getName()+"_"+work.getName()+work.getExtensionName();}
                case 3:{return student.getName()+"_"+student.getId()+"_"+work.getName()+work.getExtensionName();}
                case 4:{return student.getName()+"_"+work.getName()+work.getExtensionName();}
                case 5:{return student.getId()+"_"+work.getName()+work.getExtensionName();}
            }
        }else{
            throw new WorkOrStudentNullException(HttpStatus.INTERNAL_SERVER_ERROR,"发生内部错误，请联系管理员。");
        }
        return null;
    }


    /**
     * 获取文件扩展名
     * @param file 文件对象
     * @return  文件扩展名
     */
    public static String getExtensionName(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String extensionName = "";
        if(originalFilename!=null){
            int i = originalFilename.lastIndexOf(".");
            if (i != -1) {
                extensionName = file.getOriginalFilename().substring(i);
            }
        }
        return extensionName;

    }
}
