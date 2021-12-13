package com.ethan.mall.admin.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ethan
 * @Date 9:52 PM 2021/12/13
 * @Description Oss对象存储Post方式上传参数
 */
@Data
public class OssPostUploadData {
    @ApiModelProperty("Bucket拥有者的AccessKey ID")
    private String oSSAccessKeyId;
    @ApiModelProperty("Policy规定了请求表单域的合法性")
    private String policy;
    @ApiModelProperty("根据AccessKey Secret和Policy计算的签名信息，OSS验证该签名信息从而验证该Post请求的合法性")
    private String signature;
    @ApiModelProperty("上传文件夹路径前缀")
    private String dir;
    @ApiModelProperty("oss对外服务的访问域名")
    private String host;
}
