package com.teoan.tclass.work.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Teoan
 * @date 2021/5/21 17:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkDTO {

    private Integer id;
    /**
     * 发布者
     */
    private Integer sId;
    /**
     * 发布者名字
     */
    private String sName;

    /**
     * 作业编辑人Id
     */
    private Integer editSId;

    /**
     * 作业编辑人名字
     */
    private String editorName;

    /**
     * 作业名称
     */
    private String name;
    /**
     * 文件名规则枚举值
     */
    private Integer fileNameFormatEnum;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 最晚提交时间
     */
    private Date lastTime;
    /**
     * 备注
     */
    private String remarks;

    /**
     * 文件拓展名id
     */
    private Integer extensionId;

    /**
     * 文件拓展名
     */
    private String extensionName;

    /**
     * 已提交文件数量
     */
    private Integer uploadFileCount;



}
