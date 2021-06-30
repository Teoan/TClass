package com.teoan.tclass.work.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.teoan.tclass.common.annotation.TclassLog;
import com.teoan.tclass.common.enums.LogSeverity;
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
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
    @TclassLog(operSource = "work",operName = "查询",severity = LogSeverity.INFO,description = "分页查询所有作业数据")
    public R selectAll(Long current, Long size, WorkDTO workDTO) {
        Work work = new Work();
        BeanUtils.copyProperties(workDTO,work);
        return R.ok(workService.getWorksByPage(current,size,work));
    }

    @Override
    @TclassLog(operSource = "work",operName = "查询",severity = LogSeverity.INFO,description = "获取拓展名列表")
    public R getExtensionList() {
        return R.ok(extensionService.list());
    }

    @Override
    @TclassLog(operSource = "work",operName = "添加",severity = LogSeverity.INFO,description = "学生上传作业")
    public R uploadWorkFile(Integer wId, MultipartFile file, Integer sId) {

        if(uploadService.uploadFile(wId,sId,file)){
            return R.success("上传成功");
        }
        return R.failed("上传失败!");
    }

    @Override
    @TclassLog(operSource = "work",operName = "查询",severity = LogSeverity.INFO,description = "检查用户是否已上传文件")
    public R isUploadedWorkFile(Integer wId, Integer sId) {
        return R.ok(uploadService.isUploadedWorkFile(wId,sId));
    }

    @Override
    @TclassLog(operSource = "work",operName = "删除",severity = LogSeverity.INFO,description = "删除已上传的文件")
    public R deleteUploadFile(UploadDTO uploadDTO) {
        Upload upload = new Upload();
        BeanUtils.copyProperties(uploadDTO,upload);
        return R.ok(uploadService.deleteUploadFile(upload));
    }

    @Override
    @TclassLog(operSource = "work",operName = "查询",severity = LogSeverity.INFO,description = "通过主键查询单条数据")
    public R selectOne(Serializable id) {
        return R.ok(workService.getById(id));
    }

    @Override
    @TclassLog(operSource = "work",operName = "新增",severity = LogSeverity.INFO,description = "新增数据")
    public R insert(WorkDTO workDTO) {
        Work work = new Work();
        BeanUtils.copyProperties(workDTO,work);
        return R.ok(workService.save(work));
    }

    @Override
    @TclassLog(operSource = "work",operName = "修改",severity = LogSeverity.INFO,description = "修改数据")
    public R update(WorkDTO workDTO) {
        Work work = new Work();
        BeanUtils.copyProperties(workDTO,work);
        return R.ok(workService.updateById(work));
    }

    @Override
    @TclassLog(operSource = "work",operName = "删除",severity = LogSeverity.INFO,description = "根据id列表删除数据")
    public R delete(List<Integer> idList) {
        return R.ok(workService.removeByIds(idList));
    }

    @Override
    public void downloadWorkFile(String fileName, Integer wId, HttpServletResponse response) {
        byte[] fileByte = fileService.getFile(wId,fileName);
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM.getType());
        try {
            //设置Content-Disposition为文件名
            response.addHeader("Content-Disposition",URLEncoder.encode(fileName,"UTF-8"));
            ServletOutputStream outputStream = response.getOutputStream();
            IOUtils.write(fileByte,outputStream);
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void downloadZipWorkFile(Integer wId,HttpServletResponse response) {
        Work work = workService.getById(wId);
        File file =  fileService.getZipByWId(wId);
        if(ObjectUtils.isNotEmpty(file)){
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM.getType());
            try {
                response.addHeader("Content-Disposition",URLEncoder.encode(work.getName()+".zip","UTF-8"));
                ServletOutputStream outputStream = response.getOutputStream();
                IOUtils.copy(new FileInputStream(file),outputStream);
                response.flushBuffer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    @TclassLog(operSource = "work",operName = "查询",severity = LogSeverity.INFO,description = "获取上传文件信息并分页")
    public R getUploadFileInfo(Integer wId, Long current, Long size) {
        return R.ok(uploadService.getUploadPageByWId(current,size,wId));
    }

    @Override
    @TclassLog(operSource = "work",operName = "删除",severity = LogSeverity.INFO,description = "根据上传文件信息列表批量删除文件")
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
    @TclassLog(operSource = "work",operName = "删除",severity = LogSeverity.INFO,description = "根据id删除扩展名")
    public R deleteExtensionById(ExtensionDTO extensionDTO) {
        return R.ok(extensionService.removeById(extensionDTO.getId()));
    }

    @Override
    @TclassLog(operSource = "work",operName = "新增",severity = LogSeverity.INFO,description = "添加扩展名")
    public R addExtension(ExtensionDTO extensionDTO) {
        Extension extension = new Extension();
        BeanUtils.copyProperties(extensionDTO,extension);
        return R.ok(extensionService.save(extension));
    }
}
