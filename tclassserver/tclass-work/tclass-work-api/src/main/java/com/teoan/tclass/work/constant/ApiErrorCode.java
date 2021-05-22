package com.teoan.tclass.work.constant;


/**
 * @author Teoan
 * @description
 * @date 2021/5/20 21:31
 */
public enum ApiErrorCode {
    /**
     * 失败
     */
    FAILED(500, "操作失败"),
    /**
     * 成功
     */
    SUCCESS(200, "执行成功");

    private final long code;
    private final String msg;

    ApiErrorCode(final long code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ApiErrorCode fromCode(long code) {
        ApiErrorCode[] ecs = ApiErrorCode.values();
        for (ApiErrorCode ec : ecs) {
            if (ec.getCode() == code) {
                return ec;
            }
        }
        return SUCCESS;
    }

    public long getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return String.format(" ErrorCode:{code=%s, msg=%s} ", code, msg);
    }
}
