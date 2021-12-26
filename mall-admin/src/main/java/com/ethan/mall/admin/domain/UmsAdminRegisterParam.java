package com.ethan.mall.admin.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author ethan
 * @Date 11:45 上午 2021/11/28
 * @Description 后台用户注册参数
 */
@Data
public class UmsAdminRegisterParam {
    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank
    private String username;
    @ApiModelProperty(value = "密码", required = true)
    @NotBlank
    private String password;
    @ApiModelProperty(value = "用户头像")
    private String icon;
    @Email
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "用户昵称")
    private String nickName;
    @ApiModelProperty(value = "备注")
    private String note;
    @ApiModelProperty(value = "启用状态")
    private Integer status;
}
