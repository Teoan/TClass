package com.teoan.tclass.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Teoan
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