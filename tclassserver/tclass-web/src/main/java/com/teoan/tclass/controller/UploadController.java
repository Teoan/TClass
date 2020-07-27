package com.teoan.tclass.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teoan.tclass.entity.Upload;
import com.teoan.tclass.service.UploadService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Upload)表控制层
 *
 * @author Teoan
 * @since 2020-07-25 15:11:00
 */
@RestController
@RequestMapping("/upload")
public class UploadController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private UploadService uploadService;

    /**
     * 分页查询所有数据
     *
     * @param page   分页对象
     * @param upload 查询实体
     * @return 所有数据
     */
    @GetMapping("/")
    public R selectAll(Page<Upload> page, Upload upload) {
        return success(this.uploadService.page(page, new QueryWrapper<>(upload)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.uploadService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param upload 实体对象
     * @return 新增结果
     */
    @PostMapping("/")
    public R insert(@RequestBody Upload upload) {
        return success(this.uploadService.save(upload));
    }

    /**
     * 修改数据
     *
     * @param upload 实体对象
     * @return 修改结果
     */
    @PutMapping("/")
    public R update(@RequestBody Upload upload) {
        return success(this.uploadService.updateById(upload));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/")
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.uploadService.removeByIds(idList));
    }
}