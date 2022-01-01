package com.ethan.mall.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ethan
 * @Date 8:37 AM 2021/12/30
 * @Description 登录的用户信息
 */
@Data
@NoArgsConstructor
@ApiModel(value = "登录用户信息")
public class LoginUser {
    @ApiModelProperty(value = "用户id")
    private Long id;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "登录密码")
    private String password;
    @ApiModelProperty(value = "启用状态")
    private Integer status;
    @ApiModelProperty(value = "客户端id")
    private String clientId;
    @ApiModelProperty(value = "角色列表")
    private List<String> roles;
}
