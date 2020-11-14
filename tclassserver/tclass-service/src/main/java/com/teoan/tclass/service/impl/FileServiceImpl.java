package com.teoan.tclass.service.impl;

import com.teoan.tclass.exception.DeleteFileException;
import com.teoan.tclass.exception.DirPathIsFileException;
import com.teoan.tclass.exception.FileNotExistsException;
import com.teoan.tclass.exception.MarkDirException;
import com.teoan.tclass.service.FileService;
import com.teoan.tclass.utils.ZipUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Teoan
 * @description 文件处理实现类
 * @date 2020/11/10 20:46
 */
@Service("FileService")
public class FileServiceImpl implements FileService {

    @Value("${file.upload.url}")
    private String path;
    @Override
    public void saveFile(MultipartFile file, String fileName,Integer wId) {
        File f = new File(path);
        if(f.exists()){
            try{
                if(f.isFile()){
                    throw new DirPathIsFileException(HttpStatus.INTERNAL_SERVER_ERROR,"所创建的目录存在但是个文件，联系管理员解决此问题");
                }
                File workDir = new File(f+File.separator+wId);
                if(!workDir.exists()){
                    if(!workDir.mkdir()){
                        throw new MarkDirException(HttpStatus.INTERNAL_SERVER_ERROR,"文件目录创建失败！");
                    }
                }
                File saveFile = new File(workDir+File.separator+fileName);
                file.transferTo(saveFile);
                if(!saveFile.exists()){
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"文件上传失败！");
                }
            }catch (Exception e){
                e.printStackTrace();
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"文件操作发生错误！",e);
            }
        }else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"存储目录不存在，请联系管理员");
        }
    }

    @Override
    public boolean deleteFile(String fileName, Integer wId) {
      File deleteFile =  new File(path+File.separator+wId+File.separator+fileName);
      return deleteFile.delete();
    }

    @Override
    public File getZipByWId(Integer wId) {

        File workDir = new File(path+File.separator+wId);
        File zipDir = new File(path+File.separator+"Zip");
        File zipFile = new File(path+File.separator+"Zip"+File.separator+wId+".zip");
        if(!workDir.exists()){
            if(!workDir.mkdir()){
                throw new MarkDirException(HttpStatus.INTERNAL_SERVER_ERROR,"文件目录创建失败！");
            }
        }
        if(!zipDir.exists()){
            if(!zipDir.mkdir()){
                throw new MarkDirException(HttpStatus.INTERNAL_SERVER_ERROR,"文件目录创建失败！");
            }
        }
        File[] listFiles = workDir.listFiles();
        if (listFiles!=null){
            ZipUtils.zipFiles(zipFile,listFiles);
        }else {
            throw new FileNotExistsException(HttpStatus.INTERNAL_SERVER_ERROR,"打包目录中不存在文件！");
        }
        return zipFile;
    }

    @Override
    public File getFile(Integer wId, String fileName) {
        File file = new File(path+File.separator+wId+File.separator+fileName);
        if (file.exists()){
            return file;
        }else{
            throw new FileNotExistsException(HttpStatus.INTERNAL_SERVER_ERROR,"文件获取失败");
        }
    }
}
