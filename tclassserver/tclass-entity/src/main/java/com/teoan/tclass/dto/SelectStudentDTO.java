package com.teoan.tclass.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Teoan
 * @description 查找用户DTO
 * @date 2021/4/8 22:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SelectStudentDTO {
    /**
     * 学生名字
     */
    private String name;
    /**
     * 性别
     */
    private String gender;
    /**
     * 班级职位Id
     */
    private Integer posId;
    /**
     * 民族Id
     */
    private Integer nationId;
    /**
     * 政治面貌Id
     */
    private Integer politicId;
    /**
     * 籍贯
     */
    private String nativePlace;

}
