package com.teoan.tclass.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teoan.tclass.entity.*;
import com.teoan.tclass.service.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    /**
     * 分页查询所有数据
     *
     * @param current 分页对象
     * @param size 查询数据量
     * @return 所有数据
     */
    @GetMapping("/")
    public R getStudentByPage(@RequestParam(defaultValue = "1")Long current,@RequestParam(defaultValue = "10")Long size,Student student) {


        return success(studentService.getStudentsByPage(current,size,student));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.studentService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param student 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Student student) {

        if(studentService.addStudent(student)){
            return success("添加成功！");
        }
        return failed("添加失败，用户名重复！");
    }

    /**
     * 修改数据
     *
     * @param student 实体对象
     * @return 修改结果
     */
    @PutMapping("/")
    public R update(@RequestBody Student student) {
        return success(this.studentService.updateById(student));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/")
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.studentService.removeByIds(idList));
    }
    /**
     * 获取民族列表
     *
     * @param
     * @return 结果
     */
    @GetMapping("/nations")
    public R getNations(){
        List<Nation> nationList = nationService.list();
        return success(nationList);
    }
    /**
     * 获取部门列表
     *
     * @param
     * @return 结果
     */
    @GetMapping("/departments")
    public R getDepartments(){
        List<Department> departmentList = departmentService.list();
        return success(departmentList);
    }
    /**
     * 获取政治面貌列表
     *
     * @param
     * @return 结果
     */
    @GetMapping("/politicsstatuses")
    public R getPoliticsstatuses(){
        List<Politicsstatus> politicsstatusList = politicsstatusService.list();
        return success(politicsstatusList);
    }
    /**
     * 获取班级职位列表
     *
     * @param
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


}