package com.teoan.tclass.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.teoan.tclass.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

/**
 * @author zhuangjy
 * @description 学生用户类DTO
 * @date 2021/4/12 16:43
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    /**
     * 学号
     */
    private Integer id;
    /**
     * 权限Id
     */
    private Integer roleId;
    /**
     * 学生名字
     */
    private String name;
    /**
     * 性别
     */
    private String gender;
    /**
     * 民族Id
     */
    private Integer nationId;
    /**
     * 籍贯
     */
    private String nativePlace;
    /**
     * 政治面貌Id
     */
    private Integer politicId;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 电话
     */
    private String phone;
    /**
     * 家庭住址
     */
    private String address;
    /**
     * 用户头像地址
     */
    private String avatarUrl;
    /**
     * 班级职位Id
     */
    private Integer posId;
    /**
     * 最近登录时间
     */
    private Date loginTime;
    /**
     * 权限
     */
    private Role role;

    /**
     * 所属部门id列表
     */
    private List<Integer> departmentIdList;

    public StudentDTO(Student student) {
        BeanUtils.copyProperties(student,this);
    }
}
