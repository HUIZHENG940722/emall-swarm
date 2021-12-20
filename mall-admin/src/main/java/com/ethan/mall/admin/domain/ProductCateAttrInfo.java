package com.ethan.mall.admin.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ethan
 * @Date 9:24 PM 2021/12/20
 * @Description 商品分类对应的参数信息
 */
@Data
public class ProductCateAttrInfo {
    @ApiModelProperty("商品类型参数ID")
    private Long attributeId;
    @ApiModelProperty("商品属性分类ID")
    private Long attributeCategoryId;
}
