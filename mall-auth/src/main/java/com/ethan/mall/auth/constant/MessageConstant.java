package com.ethan.mall.auth.constant;

/**
 * @author ethan
 * @Date 11:05 AM 2022/1/8
 * @Description 消息常量
 */
public interface MessageConstant {
    String USERNAME_PASSWORD_ERROR = "用户名或密码错误!";
    String ACCOUNT_DISABLED = "该账户已被禁用，请联系管理员!";
    String ACCOUNT_LOCKED = "该账号已被锁定，请联系管理员!";
    String ACCOUNT_EXPIRED = "该账号已过期，请联系管理员!";
    String CREDENTIALS_EXPIRED = "该账户的登录凭证已过期，请重新登录!";
}
