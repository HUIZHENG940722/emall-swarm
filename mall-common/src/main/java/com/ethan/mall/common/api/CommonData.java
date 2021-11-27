package com.ethan.mall.common.api;

import lombok.Data;

/**
 * @author ethan
 * @Date 6:56 上午 2021/10/7
 * @Description 公共返回数据封装
 */
@Data
public class CommonData<T> {
    /**
     * 返回结果码
     */
    private String code;
    /**
     * 返回结果提示信息
     */
    private String message;
    /**
     * 返回结果数据
     */
    private T data;

    public CommonData() {
    }

    public CommonData(String code, String message, T date) {
        this.code = code;
        this.message = message;
        this.data = date;
    }

    /**
     * 成功返回结果数据
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonData<T> success(T data) {
        return new CommonData<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 失败返回结果
     * @param resultCode 错误码
     */
    public static <T> CommonData<T> failed(IResultCode resultCode) {
        return new CommonData<>(resultCode.getCode(), resultCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> CommonData<T> failed(String message) {
        return new CommonData<>(ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     * @param <T>
     * @return
     */
    public static <T> CommonData<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     * @param message 提示信息
     * @param <T>
     * @return
     */
    public static <T> CommonData<T> validateFailed(String message) {
        return new CommonData<>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonData<T> unauthorized(T data) {
        return new CommonData<>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonData<T> forbidden(T data) {
        return new CommonData<>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }
}
