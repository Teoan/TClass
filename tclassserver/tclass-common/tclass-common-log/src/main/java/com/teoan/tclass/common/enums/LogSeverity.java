package com.teoan.tclass.common.enums;

/**
 * @author Teoan
 * @description 日志级别枚举
 * @date 2021/6/25 15:54
 */

public enum LogSeverity {
    /**
     * 日志状态类型
     */
    WARNING("警告日志"),
    ERROR("错误日志"),
    INFO("信息日志");

    private String severity;
    LogSeverity(String severity) {
        this.severity = severity;
    }

    public String getSeverity() {
        return severity;
    }
}
