package com.teoan.tclass.controller.admin;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.teoan.tclass.entity.Extension;
import com.teoan.tclass.entity.Upload;
import com.teoan.tclass.entity.Work;
import com.teoan.tclass.exception.FileException;
import com.teoan.tclass.service.ExtensionService;
import com.teoan.tclass.service.FileService;
import com.teoan.tclass.service.UploadService;
import com.teoan.tclass.service.WorkService;
import org.apache.poi.util.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * (Work)表控制层
 *
 * @author Teoan
 * @since 2020-07-25 15:11:13
 */
@RestController
@RequestMapping("/admin/work")
public class AdWorkController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private WorkService workService;

    /**
     * 文件服务对象
     */
    @Resource
    private FileService fileService;

    /**
     * 上传信息服务对象
     */
    @Resource
    private UploadService uploadService;

    /**
     * 扩展名服务对象
     */
    @Resource
    private ExtensionService extensionService;


    /**
     * 新增数据
     *
     * @param work 实体对象
     * @return 新增结果
     */
    @PostMapping("/")
    public R insert(@RequestBody Work work) {
        return success(this.workService.save(work));
    }

    /**
     * 修改数据
     *
     * @param work 实体对象
     * @return 修改结果
     */
    @PutMapping("/")
    public R update(@RequestBody Work work) {
        return success(this.workService.updateById(work));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/")
    public R delete(@RequestParam("idList") List<Integer> idList) {
        return success(this.workService.removeByIds(idList));
    }


    /**
     * 文件下载
     * @param fileName 文件名
     * @param wId 作业id
     */
    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadWorkFile(@RequestParam("fileName")String fileName, @RequestParam("wId")Integer wId) throws UnsupportedEncodingException {
        File file = fileService.getFile(wId,fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment",URLEncoder.encode(fileName,"UTF-8"));

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            return new ResponseEntity<byte[]>(IOUtils.toByteArray(fileInputStream),headers,HttpStatus.OK);
        } catch (Exception e) {
            throw new FileException(HttpStatus.INTERNAL_SERVER_ERROR,"文件未找到,下载失败!");
        }
    }

    /**
     * 文件打包下载
     * @param wId 作业id
     */
    @GetMapping("/download/{wId}")
    public ResponseEntity<byte[]> downloadZipWorkFile(@PathVariable("wId")Integer wId) throws UnsupportedEncodingException {
        Work work = workService.getById(wId);
        File file =  fileService.getZipByWId(wId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment",URLEncoder.encode(work.getName()+".zip","UTF-8"));
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            return new ResponseEntity<byte[]>(IOUtils.toByteArray(fileInputStream),headers,HttpStatus.OK);
        } catch (Exception e) {
            throw new FileException(HttpStatus.INTERNAL_SERVER_ERROR,"文件未找到,下载失败!");
        }
    }

    /**
     * 获取上传文件信息并分页
     * @param wId 作业id
     * @param current 当前页
     * @param size 页大小
     * @return 文件信息分页
     */
    @GetMapping("/upload/{wId}")
    public R getUploadFileInfo(@PathVariable("wId")Integer wId,@RequestParam(defaultValue = "1")Long current,@RequestParam(defaultValue = "10")Long size){
        return success(uploadService.getUploadPageByWId(current, size, wId));
    }

    /**
     * 根据上传文件信息列表批量删除文件
     * @param deleteFileNameList 删除文件名列表
     * @param wId 作业id
     * @param deleteSIdList 学号列表
     */
    @DeleteMapping("/upload")
    public R deleteUploadFileByIdList(@RequestParam("deleteSIdList") List<Integer> deleteSIdList,@RequestParam("wId") Integer wId,@RequestParam("deleteFileNameList") List<String> deleteFileNameList){
        if(deleteSIdList.size()==deleteFileNameList.size()){
            for(int i = 0;i<deleteFileNameList.size();i++){
                if(!uploadService.deleteUploadFile(wId,deleteSIdList.get(i),deleteFileNameList.get(i)))
                return R.failed("文件删除失败！");
            }
        }
        return success(true).setMsg("删除成功！");
    }


    /**
     * 根据id删除扩展名
     * @param eId 扩展名id
     * @return 是否成功
     */
    @DeleteMapping("/extensions")
    public R deleteExtensionById(@RequestParam("eId")Integer eId){
        return R.ok(extensionService.removeById(eId));
    }

    /**
     * 添加扩展名
     * @param extension 扩展名
     * @return 是否成功
     */
    @PostMapping("/extensions")
    public R addExtension(@RequestBody Extension extension){
        return R.ok(extensionService.save(extension));
    }
}