package com.teoan.tclass.work.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (Work)表实体类
 *
 * @author Teoan
 * @since 2021-05-19 17:28:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName(value = "work",resultMap = "WorkMap")
public class Work extends Model<Work> {

    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 发布者
     */
    private Integer sId;
    /**
     * 发布者名字
     */
    @TableField(exist = false)
    private String sName;

    /**
     * 作业编辑人Id
     */
    private Integer editSId;

    /**
     * 作业编辑人名字
     */
    @TableField(exist = false)
    private String editorName;

    /**
     * 作业启用状态
     */
    @TableLogic
    private Boolean deleted;
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
    @TableField(fill = FieldFill.DEFAULT)
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.DEFAULT)
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
    @TableField(exist = false)
    private String extensionName;

    /**
     * 已提交文件数量
     */
    @TableField(exist = false)
    private Integer uploadFileCount;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }


    public String getExtensionName() {
        if(StringUtils.isNotBlank(extensionName)){
            extensionName = extensionName.substring(extensionName.indexOf('.')+1);
        }
        return extensionName;
    }
}
