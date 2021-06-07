package com.teoan.tclass.oauth.dto;

import com.teoan.tclass.common.entity.SysRole;
import lombok.Data;

import java.util.Date;

/**
 * @author Teoan
 * @date 2021/6/7 16:36
 */
@Data
public class CurrentUserDTO {
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
     * 权限信息
     */
    private SysRole role;

}
