package com.teoan.tclass.work.service;

import com.teoan.tclass.common.result.R;
import com.teoan.tclass.work.dto.ExtensionDTO;
import com.teoan.tclass.work.dto.UploadDTO;
import com.teoan.tclass.work.dto.WorkDTO;
import com.teoan.tclass.work.service.fallback.WorkFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

/**
 * @author Teoan
 * @date 2021/5/21 17:38
 */
@FeignClient(name="tclass-work-server",value = "tclass-work-server",path = "/work",fallback = WorkFeignFallback.class)
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


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/admin/{id}")
    public R selectOne(@PathVariable Serializable id);

    /**
     * 新增数据
     *
     * @param work 实体对象
     * @return 新增结果
     */
    @PostMapping("/admin/")
    public R insert(@RequestBody WorkDTO work);

    /**
     * 修改数据
     *
     * @param work 实体对象
     * @return 修改结果
     */
    @PutMapping("/admin/")
    public R update(@RequestBody WorkDTO work);

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/admin/")
    public R delete(@RequestBody List<Integer> idList);


    /**
     * 文件下载
     * @param fileName 文件名
     * @param wId 作业id
     */
    @GetMapping("/admin/download")
    public ResponseEntity<byte[]> downloadWorkFile(@RequestParam("fileName")String fileName, @RequestParam("wId")Integer wId);

    /**
     * 文件打包下载
     * @param wId 作业id
     */
    @GetMapping("/admin/download/{wId}")
    public ResponseEntity<byte[]> downloadZipWorkFile(@PathVariable("wId")Integer wId);

    /**
     * 获取上传文件信息并分页
     * @param wId 作业id
     * @param current 当前页
     * @param size 页大小
     * @return 文件信息分页
     */
    @GetMapping("/admin/upload/{wId}")
    public R getUploadFileInfo(@PathVariable("wId")Integer wId,@RequestParam(defaultValue = "1")Long current,@RequestParam(defaultValue = "10")Long size);

    /**
     * 根据上传文件信息列表批量删除文件
     * @param uploadList 删除文件名列表
     */
    @DeleteMapping("/admin/upload")
    public R deleteUploadFileByIdList(@RequestBody List<UploadDTO> uploadList);


    /**
     * 根据id删除扩展名
     * @param extension 扩展名id
     * @return 是否成功
     */
    @DeleteMapping("/admin/extensions")
    public R deleteExtensionById(@RequestBody ExtensionDTO extension);

    /**
     * 添加扩展名
     * @param extension 扩展名
     * @return 是否成功
     */
    @PostMapping("/admin/extensions")
    public R addExtension(@RequestBody ExtensionDTO extension);
}
