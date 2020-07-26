package com.teoan.tclass.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * (Student)实体类
 *
 * @author Teoan
 * @since 2020-07-24 16:44:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName(value ="student",resultMap = "AllStudentInfo")
public class Student extends Model<Student> {

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
    * 班级职位Id
    */
    private Integer posId;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 更新时间
    */
    private Date updateTime;
    /**
    * 最近登录时间
    */
    private Date loginTime;

    /**
     * 权限
     */
    @TableField(exist = false)
    private Role role;
    /**
     * 民族
     */
    @TableField(exist = false)
    private Nation nation;
    /**
     * 政治面貌
     */
    @TableField(exist = false)
    private Politicsstatus politicsstatus;
    /**
     * 班级职位
     */
    @TableField(exist = false)
    private Position position;
    /**
     * 所属部门
     */
    @TableField(exist = false)
    private List<Department> departmentList;
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