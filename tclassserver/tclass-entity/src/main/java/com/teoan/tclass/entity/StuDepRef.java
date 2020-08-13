package com.teoan.tclass.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Teoan
 * @description
 * @date 2020/8/13 12:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StuDepRef {
    private Integer sId;
    private Integer dId;
}
