package com.ethan.mall.admin.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * @author ethan
 * @Date 10:03 AM 2021/12/12
 * @Description 商品品牌添加参数
 */
@Data
public class PmsBrandCreateParam {
    @NotEmpty
    @ApiModelProperty(value = "品牌名称",required = true)
    private String name;
    @ApiModelProperty(value = "品牌首字母")
    private String firstLetter;
    @Min(value = 0)
    @ApiModelProperty(value = "排序字段")
    private Integer sort;
    @ApiModelProperty(value = "是否为厂家制造商")
    private Integer factoryStatus;
    @ApiModelProperty(value = "是否进行显示")
    private Integer showStatus;
    @NotEmpty
    @ApiModelProperty(value = "品牌logo",required = true)
    private String logo;
    @ApiModelProperty(value = "品牌大图")
    private String bigPic;
    @ApiModelProperty(value = "品牌故事")
    private String brandStory;
}
