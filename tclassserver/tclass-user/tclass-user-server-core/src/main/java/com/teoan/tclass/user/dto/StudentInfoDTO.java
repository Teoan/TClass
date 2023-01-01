package com.teoan.tclass.user.dto;

import com.teoan.tclass.user.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author Teoan
 * @description 学生详情信息DTO
 * @date 2021/6/8 17:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInfoDTO {
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
     * 所属部门id列表
     */
    private List<Integer> departmentIdList;

    /**
     * 权限
     */
    private Role role;
}
