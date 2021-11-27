package com.ethan.mall.common.exception;

import com.ethan.mall.common.api.IResultCode;

/**
 * @author ethan
 * @Date 7:18 上午 2021/10/7
 * @Description 断言处理类，用于抛出各种API异常
 */
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IResultCode iResultCode) {
        throw new ApiException(iResultCode);
    }
}
