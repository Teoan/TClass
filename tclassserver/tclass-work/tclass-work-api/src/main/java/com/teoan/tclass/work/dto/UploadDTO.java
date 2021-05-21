package com.teoan.tclass.work.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Teoan
 * @date 2021/5/21 17:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UploadDTO {
    /**
     * 学生编号
     */
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
    private String sName;

}
