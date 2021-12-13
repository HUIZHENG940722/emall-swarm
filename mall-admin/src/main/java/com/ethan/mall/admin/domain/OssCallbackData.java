package com.ethan.mall.admin.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ethan
 * @Date 2:54 PM 2021/12/12
 * @Description oss上传文件的回调数据
 */
@Data
public class OssCallbackData {
    @ApiModelProperty("文件名称")
    private String filename;
    @ApiModelProperty("文件大小")
    private String size;
    @ApiModelProperty("文件的mimeType")
    private String mimeType;
    @ApiModelProperty("图片文件的宽")
    private String width;
    @ApiModelProperty("图片文件的高")
    private String height;
}
