package com.teoan.tclass.common.result;


/**
 * @author Teoan
 * @description
 * @date 2021/5/20 21:31
 */
public enum ApiStatusCode {
    /**
     * 失败
     */
    FAILED(500, "操作失败"),
    /**
     * 成功
     */
    SUCCESS(200, "执行成功"),

    /**
     * 权限不足
     */
    ACCESS_DENIED(403, "权限不足，无法访问！"),
    /**
     * 未授权
     */
    UNAUTHORIZED(401, "授权无效或已过期，请重新登录！");

    private final long code;
    private final String msg;

    ApiStatusCode(final long code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ApiStatusCode fromCode(long code) {
        ApiStatusCode[] ecs = ApiStatusCode.values();
        for (ApiStatusCode ec : ecs) {
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
