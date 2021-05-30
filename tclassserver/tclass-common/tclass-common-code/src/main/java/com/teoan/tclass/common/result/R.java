package com.teoan.tclass.common.result;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.Optional;

/**
 * @author Teoan
 * @date 2021/5/20 21:29
 */
@Data
public class R<T> {
    private static final long serialVersionUID = 1L;

    /**
     * 业务错误码
     */
    private long code;
    /**
     * 结果集
     */
    private T data;
    /**
     * 描述
     */
    private String msg;

    /**
     * 时间戳
     */
    private Long timeStamp = new Date().getTime();

    public R() {
        // to do nothing
    }

    public R(long code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public R(ApiStatusCode errorCode) {
        errorCode = Optional.ofNullable(errorCode).orElse(ApiStatusCode.FAILED);
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public static <T> R<T> ok(T data) {
        ApiStatusCode aec = ApiStatusCode.SUCCESS;
        if (data instanceof Boolean && Boolean.FALSE.equals(data)) {
            aec = ApiStatusCode.FAILED;
        }
        return restResult(data, aec);
    }

    public static <T> R<T> failed(String msg) {
        return restResult(null, ApiStatusCode.FAILED.getCode(), msg);
    }

    public static <T> R<T> success(String msg) {
        return restResult(null, ApiStatusCode.SUCCESS.getCode(), msg);
    }

    public static <T> R<T> failed(ApiStatusCode errorCode) {
        return restResult(null, errorCode);
    }

    public static <T> R<T> restResult(T data, ApiStatusCode errorCode) {
        return restResult(data, errorCode.getCode(), errorCode.getMsg());
    }

    private static <T> R<T> restResult(T data, long code, String msg) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public boolean ok() {
        return ApiStatusCode.SUCCESS.getCode() == code;
    }
}
