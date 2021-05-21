package com.teoan.tclass.work.service;

import com.teoan.tclass.work.constant.R;
import com.teoan.tclass.work.dto.UploadDTO;
import com.teoan.tclass.work.dto.WorkDTO;
import com.teoan.tclass.work.service.fallback.WorkFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Teoan
 * @date 2021/5/21 17:38
 */
@FeignClient(name="tclass-work-server",value = "tclass-work-server",fallback = WorkFeignFallback.class)
public interface WorkFeignClient {
    /**
     * extensionService分页查询所有数据
     *
     * @param current 分页对象
     * @param size 查询数据量
     * @return 所有数据
     */
    @GetMapping("/")
    R selectAll(@RequestParam(value = "current",defaultValue = "1")Long current, @RequestParam(value = "size",defaultValue = "10")Long size, WorkDTO work);


    /**
     * 获取拓展名列表
     *
     * @return 拓展名列表
     */
    @GetMapping("/extensions")
    R getExtensionList();

    /**
     * 学生上传作业
     *
     * @return 上传结果
     */
    @PostMapping("/{wId}")
    R uploadWorkFile(@PathVariable("wId")Integer wId, @RequestParam("file") MultipartFile file, @RequestParam("sId")Integer sId);


    /**
     * 检查用户是否已上传文件
     * @param wId 作业id
     * @param sId 学号
     * @return 文件信息
     */
    @GetMapping("/isUpload")
    R isUploadedWorkFile(@RequestParam("wId")Integer wId, @RequestParam("sId")Integer sId);


    /**
     * 删除已上传的文件
     * @param upload 上传文件对象
     * @return 是否成功
     */
    @DeleteMapping("/deleteFile")
    R deleteUploadFile(@RequestBody UploadDTO upload);
}
