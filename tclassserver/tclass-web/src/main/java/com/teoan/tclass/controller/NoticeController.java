package com.teoan.tclass.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teoan.tclass.entity.Notice;
import com.teoan.tclass.service.NoticeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Notice)表控制层
 *
 * @author Teoan
 * @since 2020-07-25 15:11:32
 */
@RestController
@RequestMapping("/notice")
public class NoticeController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private NoticeService noticeService;

    /**
     * 分页查询所有数据
     *
     * @param current 分页对象
     * @param size 查询数据量
     * @return 所有数据
     */
    @GetMapping("/")
    public R selectNoticeByPage(@RequestParam(defaultValue = "1")Long current,@RequestParam(defaultValue = "10")Long size, Notice notice) {
        return success(noticeService.selectNoticesByPage(current,size,notice));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.noticeService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param notice 实体对象
     * @return 新增结果
     */
    @PostMapping("/")
    public R insert(@RequestBody Notice notice) {
        return success(this.noticeService.save(notice));
    }

    /**
     * 修改数据
     *
     * @param notice 实体对象
     * @return 修改结果
     */
    @PutMapping("/")
    public R update(@RequestBody Notice notice) {
        return success(this.noticeService.updateById(notice));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/")
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.noticeService.removeByIds(idList));
    }
}