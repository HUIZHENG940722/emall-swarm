package com.ethan.mall.common.exception;

import com.ethan.mall.common.api.IResultCode;
import lombok.Data;

/**
 * @author ethan
 * @Date 7:13 上午 2021/10/7
 * @Description 自定义API异常
 */
@Data
public class ApiException extends RuntimeException{
    private IResultCode iResultCode;

    public ApiException(IResultCode iResultCode) {
        this.iResultCode = iResultCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public IResultCode getIResultCode() {
        return iResultCode;
    }
}
