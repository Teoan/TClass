package com.teoan.tclass.user.service;


import com.teoan.tclass.user.constant.R;
import com.teoan.tclass.user.dto.StudentDTO;
import com.teoan.tclass.user.service.fallback.StudentFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;

/**
 * @author Teoan
 * @description 学生Feign客户端
 * @date 2021/5/21 11:18
 */
@FeignClient(name="tclass-user-server",value = "tclass-user-server",fallback = StudentFeignFallback.class)
public interface StudentFeignClient {
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    R selectOne(@PathVariable Serializable id);

    /**
     * 修改数据
     *
     * @param studentDTO 实体DTO对象
     * @return 修改结果
     */
    @PutMapping("/")
    public R update(@RequestBody StudentDTO studentDTO);

    /**
     * 获取民族列表
     * @return 结果
     */
    @GetMapping("/nations")
    R getNations();
    /**
     * 获取部门列表
     * @return 结果
     */
    @GetMapping("/departments")
    R getDepartments();
    /**
     * 获取政治面貌列表
     * @return 结果
     */
    @GetMapping("/political")
    R getPoliticsStatusList();
    /**
     * 获取班级职位列表
     * @return 结果
     */
    @GetMapping("/positions")
    R getPosition();

    /**
     * 获取权限列表
     * @return 结果
     */
    @GetMapping("/roles")
    R getRoles();


    /**
     * 更新用户头像
     * @param avatarFile 用户头像文件
     */
    @PostMapping("/avatar")
    R updateUserAvatar(@RequestParam("file") MultipartFile avatarFile);

    /**
     * 获取用户头像
     * @param photoPath 图片路径
     */
    @GetMapping("/avatar/{photoPath}")
    void getUserAvatar(HttpServletResponse resp , @PathVariable("photoPath") String photoPath);


    /**
     * 新增数据
     *
     * @param student 实体对象
     * @return 新增结果
     */
    @PostMapping("/admin")
    R insert(@RequestBody StudentDTO student);

    /**
     * 分页查询所有数据
     *
     * @param current 分页对象
     * @param size 查询数据量
     * @return 所有数据
     */
    @GetMapping("/admin/")
    R getStudentByPage(@RequestParam(defaultValue = "1")Long current, @RequestParam(defaultValue = "10")Long size, StudentDTO studentDTO);


    /**
     * 修改数据
     *
     * @param studentDTO 实体DTO
     * @return 修改结果
     */
    @PutMapping("/admin/")
    R adminUpdate(@RequestBody StudentDTO studentDTO);

    /**
     * 重置密码
     *
     * @param SId 实体id
     * @return 重置结果
     */
    @PutMapping("/admin/reSetPassword/{SId}")
    R reSetPassword(@PathVariable("SId") Long SId);

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/admin/")
    R delete(@RequestBody List<Long> idList);

    /**
     * 导出学生数据
     */
    @GetMapping("/admin/export")
    ResponseEntity<byte[]> exportData(@RequestParam("idList") List<Long> idList);

    /**
     * 导入学生数据
     */
    @PostMapping("/admin/import")
    R importData(MultipartFile file);



    @GetMapping("/admin/template")
    ResponseEntity<byte[]> getTemplate();
}
