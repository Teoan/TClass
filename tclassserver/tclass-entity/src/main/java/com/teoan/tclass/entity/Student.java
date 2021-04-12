package com.teoan.tclass.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
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
@JsonIgnoreProperties({"accountNonExpired","accountNonLocked","username","authorities","credentialsNonExpired","enabled"})
public class Student extends Model<Student> implements UserDetails {

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
    * 创建时间
    */
    @TableField(fill = FieldFill.DEFAULT)
    private Date createTime;
    /**
    * 更新时间
    */
    @TableField(fill = FieldFill.DEFAULT)
    private Date updateTime;
    /**
    * 最近登录时间
    */
    private Date loginTime;
    /**
     * 用户是否可用
     */
    @TableLogic
    private boolean deleted;

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
     * 所属部门id列表
     */
    @TableField(exist = false)
    private List<Integer> departmentIdList;


    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }


    //springSecurity

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(role.getName()));
        return authorityList;
    }

    //登录使用学号登录，所以这里用户名返回的是学号
    @Override
    public String getUsername() {
        return String.valueOf(this.id);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !deleted;
    }
}