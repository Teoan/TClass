package com.teoan.tclass.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Teoan
 * @date 2021/5/20 15:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeDTO {
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
    private String sName;

    /**
     * 通知编辑人名字
     */
    private String editSName;

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
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
