package com.teoan.tclass.controller.admin;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.teoan.tclass.entity.Notice;
import com.teoan.tclass.service.NoticeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Notice)表控制层
 *
 * @author Teoan
 * @since 2020-07-25 15:11:32
 */
@RestController
@RequestMapping("/admin/notice")
public class AdNoticeController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private NoticeService noticeService;

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
    public R delete(@RequestBody List<Long> idList) {
        return success(this.noticeService.removeByIds(idList));
    }
}