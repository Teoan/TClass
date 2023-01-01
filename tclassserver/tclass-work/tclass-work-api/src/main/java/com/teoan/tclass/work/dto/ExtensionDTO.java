package com.teoan.tclass.work.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Teoan
 * @description
 * @date 2021/5/22 11:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExtensionDTO {
    private Integer id;

    private String name;
}
