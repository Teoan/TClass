package com.teoan.tclass.common.annotation;

import com.teoan.tclass.common.enums.LogSeverity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Teoan
 * @description 日志注解
 * @date 2021/6/25 15:41
 */
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface TclassLog {

    /**
     * 日志来源 操作模块
     *
     * @return
     */
    String operSource() default "";

    /**
     * 日志级别
     *
     * @return
     */
    LogSeverity severity() default LogSeverity.WARNING;

    /**
     * 日志操作名称
     *
     * @return
     */
    String operName() default "";

    /**
     * 日志操作描述
     *
     * @return
     */
    String description() default "";
}
