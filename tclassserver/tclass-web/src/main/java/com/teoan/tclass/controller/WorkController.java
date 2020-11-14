package com.teoan.tclass.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teoan.tclass.entity.Extension;
import com.teoan.tclass.entity.Work;
import com.teoan.tclass.exception.FileException;
import com.teoan.tclass.service.ExtensionService;
import com.teoan.tclass.service.UploadService;
import com.teoan.tclass.service.WorkService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Work)表控制层
 *
 * @author Teoan
 * @since 2020-07-25 15:11:13
 */
@RestController
@RequestMapping("/work")
public class WorkController extends ApiController {
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


    /**
     * 分页查询所有数据
     *
     * @param current 分页对象
     * @param size 查询数据量
     * @return 所有数据
     */
    @GetMapping("/")
    public R selectAll(@RequestParam(value = "current",defaultValue = "1")Long current,@RequestParam(value = "size",defaultValue = "10")Long size,Work work) {
        IPage worksByPage = workService.getWorksByPage(current, size, work);
        return success(worksByPage);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.workService.getById(id));
    }

    /**
     * 获取拓展名列表
     *
     * @return 拓展名列表
     */
    @GetMapping("/extensions")
    public R getExtensionList(){
        List<Extension> extensionList = extensionService.list();
        return success(extensionList);
    }

    /**
     * 学生上传作业
     *
     * @return 上传结果
     */
    @PostMapping("/{wId}")
    public R uploadWorkFile(@PathVariable("wId")Integer wId, @RequestParam("file")MultipartFile file,@RequestParam("sId")Integer sId){
        uploadService.uploadFile(wId,sId,file);
        return R.ok("上传成功");
    }


    /**
     * 检查用户是否已上传过文件
     * @param wId 作业id
     * @param sId 学号
     * @return 文件信息
     */
    @GetMapping("/isUpload")
    public R isUploadedWorkFile(@RequestParam("wId")Integer wId,@RequestParam("sId")Integer sId){
        return R.ok(uploadService.isUploadedWorkFile(wId,sId));
    }


    /**
     * 删除已上传的文件
     * @param wId 文件id
     * @param sId 学号
     * @param fileName 文件名
     * @return 是否成功
     */
    @DeleteMapping("/deleteFile")
    public R deleteUploadFile(@RequestParam("wId")Integer wId,@RequestParam("sId")Integer sId,@RequestParam("fileName")String fileName){
        return R.ok(uploadService.deleteUploadFile(wId,sId,fileName));
    }

}