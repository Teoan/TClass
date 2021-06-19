package com.teoan.tclass.work.utils;

import com.teoan.tclass.work.entity.Upload;
import com.teoan.tclass.work.exception.ZipFileException;
import org.springframework.http.HttpStatus;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Teoan
 * @date 2020/11/14 15:03
 */
public class ZipUtils {


    /**
     * 将文件打包为zip
     * @param zipFile zip文件
     * @param uploadFiles 要打包的文件
     */
    public static void zipFiles(File zipFile, List<Upload> uploadFiles){
        try{
            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(fos));
            for(Upload upload:uploadFiles){
                String fileName = upload.getFileName();
                ZipEntry zipEntry = new ZipEntry(fileName);
                zos.putNextEntry(zipEntry);
                zos.write(upload.getFileByte());
            }
            zos.closeEntry();
            zos.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new ZipFileException(HttpStatus.INTERNAL_SERVER_ERROR,"文件打包失败");
        }
    }
}
