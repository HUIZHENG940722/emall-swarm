package com.ethan.mall.auth.exception;

import com.ethan.mall.common.api.CommonData;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ethan
 * @Date 4:38 PM 2022/1/8
 * @Description 全局处理Oauth2ExceptionHandler
 */
@RestControllerAdvice
public class Oauth2ExceptionHandler {
    @ExceptionHandler(value = OAuth2Exception.class)
    public CommonData<String> handlerOauth2(OAuth2Exception e) {
        return CommonData.failed(e.getMessage());
    }
}
