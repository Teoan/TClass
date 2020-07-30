package com.teoan.tclass.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teoan.tclass.entity.Work;
import com.teoan.tclass.service.WorkService;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/admin/work")
public class AdWorkController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private WorkService workService;


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
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.workService.removeByIds(idList));
    }
}