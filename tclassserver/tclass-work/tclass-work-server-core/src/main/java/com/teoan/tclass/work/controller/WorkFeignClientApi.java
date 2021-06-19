package com.teoan.tclass.work.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.teoan.tclass.common.service.AuthUserService;
import com.teoan.tclass.common.result.R;
import com.teoan.tclass.work.dto.ExtensionDTO;
import com.teoan.tclass.work.dto.UploadDTO;
import com.teoan.tclass.work.dto.WorkDTO;
import com.teoan.tclass.work.entity.Extension;
import com.teoan.tclass.work.entity.Upload;
import com.teoan.tclass.work.entity.Work;
import com.teoan.tclass.work.exception.FileException;
import com.teoan.tclass.work.service.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Teoan
 * @description
 * @date 2021/5/19 22:48
 */
@RestController
public class WorkFeignClientApi implements WorkFeignClient {
    /**
     * 作业服务对象
     */
    @Resource
    private WorkService workService;
    /**
     * 上传相关服务对象
     */
    @Resource
    private UploadService uploadService;
    /**
     * 拓展名服务对象
     */
    @Resource
    private ExtensionService extensionService;

    @Resource
    private AuthUserService authUserService;

    @Resource
    private FileService fileService;

    @Override
    public R selectAll(Long current, Long size, WorkDTO workDTO) {
        Work work = new Work();
        BeanUtils.copyProperties(workDTO,work);
        return R.ok(workService.getWorksByPage(current,size,work));
    }

    @Override
    public R getExtensionList() {
        return R.ok(extensionService.list());
    }

    @Override
    public R uploadWorkFile(Integer wId, MultipartFile file, Integer sId) {

        if(uploadService.uploadFile(wId,sId,file)){
            return R.success("上传成功");
        }
        return R.failed("上传失败!");
    }

    @Override
    public R isUploadedWorkFile(Integer wId, Integer sId) {
        return R.ok(uploadService.isUploadedWorkFile(wId,sId));
    }

    @Override
    public R deleteUploadFile(UploadDTO uploadDTO) {
        Upload upload = new Upload();
        BeanUtils.copyProperties(uploadDTO,upload);
        return R.ok(uploadService.deleteUploadFile(upload));
    }

    @Override
    public R selectOne(Serializable id) {
        return R.ok(workService.getById(id));
    }

    @Override
    public R insert(WorkDTO workDTO) {
        Work work = new Work();
        BeanUtils.copyProperties(workDTO,work);
        return R.ok(workService.save(work));
    }

    @Override
    public R update(WorkDTO workDTO) {
        Work work = new Work();
        BeanUtils.copyProperties(workDTO,work);
        return R.ok(workService.updateById(work));
    }

    @Override
    public R delete(List<Integer> idList) {
        return R.ok(workService.removeByIds(idList));
    }

    @Override
    public ResponseEntity<byte[]> downloadWorkFile(String fileName, Integer wId) {
        byte[] fileByte = fileService.getFile(wId,fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        if(ObjectUtils.isNotEmpty(fileByte)){
            try {
                headers.setContentDispositionFormData("attachment", URLEncoder.encode(fileName,"UTF-8"));
                return new ResponseEntity<byte[]>(fileByte,headers, HttpStatus.OK);
            } catch (Exception e) {
                throw new FileException(HttpStatus.INTERNAL_SERVER_ERROR,"文件未找到,下载失败!");
            }
        }
        return null;
    }

    @Override
    public ResponseEntity<byte[]> downloadZipWorkFile(Integer wId) {
        Work work = workService.getById(wId);
        File file =  fileService.getZipByWId(wId);
        if(ObjectUtils.isNotEmpty(file)){
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            try {
                headers.setContentDispositionFormData("attachment",URLEncoder.encode(work.getName()+".zip","UTF-8"));
                FileInputStream fileInputStream = new FileInputStream(file);
                return new ResponseEntity<byte[]>(IOUtils.toByteArray(fileInputStream),headers,HttpStatus.OK);
            } catch (Exception e) {
                throw new FileException(HttpStatus.INTERNAL_SERVER_ERROR,"文件未找到,下载失败!");
            }
        }
        return null;
    }

    @Override
    public R getUploadFileInfo(Integer wId, Long current, Long size) {
        return R.ok(uploadService.getUploadPageByWId(current,size,wId));
    }

    @Override
    public R testFeign() {
        return R.ok(authUserService.getCurrentUser());
    }

    @Override
    public R deleteUploadFileByIdList(List<UploadDTO> uploadDTOList) {
        List<Upload> uploadList = uploadDTOList.stream().map(uploadDTO -> {
            Upload upload = new Upload();
            BeanUtils.copyProperties(uploadDTO,upload);
            return upload;
        }).collect(Collectors.toList());
        for (Upload upload : uploadList) {
            if(!uploadService.deleteUploadFile(upload)){
                return R.failed("文件删除失败！");
            }
        }
        return R.success("删除成功");
    }

    @Override
    public R deleteExtensionById(ExtensionDTO extensionDTO) {
        return R.ok(extensionService.removeById(extensionDTO.getId()));
    }

    @Override
    public R addExtension(ExtensionDTO extensionDTO) {
        Extension extension = new Extension();
        BeanUtils.copyProperties(extensionDTO,extension);
        return R.ok(extensionService.save(extension));
    }
}
