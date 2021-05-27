package com.teoan.tclass.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Student)实体类
 *
 * @author Teoan
 * @date 2021/5/19 16:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName(value ="student")
public class Student extends Model<Student> {

    /**
     * 学号
     */
    @TableId(type = IdType.AUTO)
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
     * 密码
     */
    private String password;
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
     * 用户是否可用
     */
    @TableLogic
    private boolean deleted;


    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}