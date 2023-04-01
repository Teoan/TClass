package com.teoan.tclass.user.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * (Politicsstatus)表实体类
 *
 * @author Teoan
 * @date 2021/5/19 16:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("serial")
public class Politicsstatus extends Model<Politicsstatus> {

    private Integer id;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Politicsstatus politicsstatus = (Politicsstatus) o;
        return Objects.equals(name, politicsstatus.name);
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
}