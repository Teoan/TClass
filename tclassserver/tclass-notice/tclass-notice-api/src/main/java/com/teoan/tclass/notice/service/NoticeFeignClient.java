package com.teoan.tclass.notice.service;

import com.teoan.tclass.common.config.TokenFeignClientConfig;
import com.teoan.tclass.notice.dto.NoticeDTO;
import com.teoan.tclass.notice.service.fallback.NoticeFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import com.teoan.tclass.common.result.R;
import java.io.Serializable;
import java.util.List;

/**
 * @author Teoan
 * @date 2021/5/20 15:44
 */
@FeignClient(name = "tclass-notice-server",value = "tclass-notice-server",path = "/notice",fallback = NoticeFeignFallback.class,configuration = TokenFeignClientConfig.class)
public interface NoticeFeignClient {
    /**
     * 分页查询所有数据
     *
     * @param current 分页对象
     * @param size 查询数据量
     * @return 所有数据
     */
    @GetMapping("/")
    R selectNoticeByPage(@RequestParam(defaultValue = "1")Long current, @RequestParam(defaultValue = "10")Long size, NoticeDTO notice);

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    R selectOne(@PathVariable Serializable id);


    /**
     * 获取最新的通知
     *
     * @return 单条数据
     */
    @GetMapping("/current")
    R getCurrentNotice();


    /**
     * 新增数据
     *
     * @param notice 实体对象
     * @return 新增结果
     */
    @PostMapping("/")
    R insert(@RequestBody NoticeDTO notice);

    /**
     * 修改数据
     *
     * @param notice 实体对象
     * @return 修改结果
     */
    @PutMapping("/")
    R update(@RequestBody NoticeDTO notice);

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/")
    R delete(@RequestBody List<Long> idList);
}
