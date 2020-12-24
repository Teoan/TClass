package com.teoan.tclass.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teoan.tclass.entity.*;
import com.teoan.tclass.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * (Student)表控制层
 *
 * @author Teoan
 * @since 2020-07-25 15:10:41
 */
@RestController
@RequestMapping("/student")
public class StudentController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private StudentService studentService;

    @Resource
    private NationService nationService;

    @Resource
    private DepartmentService departmentService;

    @Resource
    private PoliticsstatusService politicsstatusService;

    @Resource
    private RoleService roleService;

    @Resource
    private PositionService positionService;

    @Resource
    private FileService fileService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.studentService.getById(id));
    }

    /**
     * 修改数据
     *
     * @param student 实体对象
     * @return 修改结果
     */
    @PutMapping("/")
    public R update(@RequestBody Student student) {
        //获取当前登录用户
        Student currentStudent = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //普通用户只能修改自己的资料信息
        if(currentStudent.getId().equals(student.getId())&&this.studentService.updateById(student)){
                return success(studentService.getById(student.getId()));
        }
        return failed("资料修改失败！");
    }

    /**
     * 获取民族列表
     * @return 结果
     */
    @GetMapping("/nations")
    public R getNations(){
        List<Nation> nationList = nationService.list();
        return success(nationList);
    }
    /**
     * 获取部门列表
     * @return 结果
     */
    @GetMapping("/departments")
    public R getDepartments(){
        List<Department> departmentList = departmentService.list();
        return success(departmentList);
    }
    /**
     * 获取政治面貌列表
     * @return 结果
     */
    @GetMapping("/political")
    public R getPoliticsStatusList(){
        List<Politicsstatus> politicsStatusList = politicsstatusService.list();
        return success(politicsStatusList);
    }
    /**
     * 获取班级职位列表
     * @return 结果
     */
    @GetMapping("/positions")
    public R getPosition(){
        List<Position> positionList = positionService.list();
        return success(positionList);
    }

    /**
     * 获取权限列表
     *
     * @param
     * @return 结果
     */
    @GetMapping("/roles")
    public R getRoles(){
        List<Role> roleList = roleService.list();
        return success(roleList);
    }


    /**
     * 更新用户头像
     * @param avatarFile 用户头像文件
     * @return
     */
    @PostMapping("/avatar")
    public R updateUserAvatar(@RequestParam("file")MultipartFile avatarFile){

        Student currentStudent = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(fileService.updateUserAvatarFile(avatarFile, currentStudent.getId())){
            studentService.updateById(Student.builder().id(currentStudent.getId()).avatarUrl("/student/avatar/"+currentStudent.getId()+".jpg").build());
        }else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"上传头像失败！");
        }
        return R.ok(true);

    }

    /**
     * 获取用户头像
     * @param photoPath 图片路径
     */
    @GetMapping("/avatar/{photoPath}")
    public void getUserAvatar(HttpServletResponse resp , @PathVariable("photoPath") String photoPath){
        File avayarFile = fileService.getUserAvatarFile(photoPath);
        if(avayarFile!=null){
            resp.setContentType("image/jpeg");
            try {
                BufferedImage bufferedImage = ImageIO.read(avayarFile);
                // 剪切图片
                int imageWidth = Math.min(bufferedImage.getWidth(), bufferedImage.getHeight());
                bufferedImage = bufferedImage.getSubimage(0,0,imageWidth,imageWidth);
                ServletOutputStream outputStream = resp.getOutputStream();
                ImageIO.write(bufferedImage,"jpg",outputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}