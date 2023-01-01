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
     * 用户头像地址
     */
    private String avatarUrl;

    /**
     * 权限信息
     */
    private SysRole role;

}
