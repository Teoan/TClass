package com.teoan.tclass.notice.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (Notice)实体类
 *
 * @author Teoan
 * @since 2020-07-24 22:46:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName(value = "notice",resultMap = "NoticeMap")
public class Notice extends Model<Notice> {

    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 通知发起人Id
     */
    private Integer sId;

    /**
     * 通知编辑人Id
     */
    private Integer editSId;
    /**
     * 通知发起人名字
     */
    @TableField(exist = false)
    private String sName;

    /**
     * 通知编辑人名字
     */
    @TableField(exist = false)
    private String editSName;
    /**
     * 逻辑删除
     */
    @TableLogic
    private Boolean deleted;

    /**
     * 标题
     */
    private String title;
    /**
     * 通知内容
     */
    private String content;
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
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
