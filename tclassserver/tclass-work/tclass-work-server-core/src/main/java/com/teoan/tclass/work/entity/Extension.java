package com.teoan.tclass.work.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Extension)表实体类
 *
 * @author Teoan
 * @since 2021-05-19 17:27:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Extension extends Model<Extension> {

    @TableId(type=IdType.AUTO)
    private Integer id;

    private String name;
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
