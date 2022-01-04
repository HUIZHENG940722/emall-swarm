package com.ethan.mall.admin.domain;

import com.ethan.mall.admin.validator.FlagValidator;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author ethan
 * @Date 10:17 PM 2021/12/9
 * @Description 创建商品分类参数
 */
@Data
public class PmsProductCategoryAddParam {
    @ApiModelProperty("父分类的编号")
    private Long parentId;
    @NotEmpty
    @ApiModelProperty(value = "商品分类名称",required = true)
    private String name;
    @ApiModelProperty("分类单位")
    private String productUnit;
    @ApiModelProperty("是否在导航栏显示")
    @FlagValidator(values = {"0","1"},message = "状态只能为0或1")
    private Integer navStatus;
    @ApiModelProperty("是否进行显示")
    @FlagValidator(values = {"0","1"},message = "状态只能为0或1")
    private Integer showStatus;
    @Min(value = 0)
    @ApiModelProperty("排序")
    private Integer sort;
    @ApiModelProperty("图标")
    private String icon;
    @ApiModelProperty("关键字")
    private String keywords;
    @ApiModelProperty("描述")
    private String description;
    @ApiModelProperty("产品相关筛选属性集合")
    private List<Long> productAttributeIdList;
}
