package com.teoan.tclass.user.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.teoan.tclass.user.entity.Nation;
import com.teoan.tclass.user.entity.Politicsstatus;
import com.teoan.tclass.user.entity.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Teoan
 * @description 学生分页数据DTO
 * @date 2021/6/9 15:42
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentPageDTO {
    /**
     * 学号
     */
    private Integer id;
    /**
     * 学生名字
     */
    private String name;
    /**
     * 性别
     */
    private String gender;
    /**
     * 籍贯
     */
    private String nativePlace;
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
     * 民族
     */
    private Nation nation;

    /**
     * 政治面貌
     */
    private Politicsstatus politicsstatus;

    /**
     * 班级职位
     */
    private Position position;
}
