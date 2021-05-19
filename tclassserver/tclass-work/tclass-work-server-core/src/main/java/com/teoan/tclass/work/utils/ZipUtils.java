package com.teoan.tclass.work.utils;

import com.teoan.tclass.work.exception.ZipFileException;
import org.springframework.http.HttpStatus;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
     * @param files 要打包的文件
     */
    public static void zipFiles(File zipFile,File[] files){

        try{
            byte[] buffer = new byte[1024];
            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(fos));
            for(File file:files){
                //不打包文件本身
                if(zipFile.getPath().equals(file.getPath())){
                    break;
                }
                String fileName = file.getName();
                ZipEntry zipEntry = new ZipEntry(fileName);
                zos.putNextEntry(zipEntry);
                FileInputStream fis = new FileInputStream(file);
                int i;
                while((i=fis.read(buffer))>0){
                    zos.write(buffer,0,i);
                }
                fis.close();
            }
            zos.closeEntry();
            zos.close();
        }catch (Exception e){
            throw new ZipFileException(HttpStatus.INTERNAL_SERVER_ERROR,"文件打包失败");
        }
    }
}
