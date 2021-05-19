package com.teoan.tclass.work.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Upload)表实体类
 *
 * @author Teoan
 * @since 2021-05-19 17:27:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("serial")
@TableName(value = "upload",resultMap = "UploadMap")
public class Upload extends Model<Upload> {
    /**
     * 学生编号
     */
    @TableId(type = IdType.NONE)
    private Integer sId;
    /**
     * 作业编号
     */
    private Integer wId;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件大小
     */
    private long size;
    /**
     * 上传时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 上传者名字
     */
    @TableField(exist = false)
    private String sName;


}
