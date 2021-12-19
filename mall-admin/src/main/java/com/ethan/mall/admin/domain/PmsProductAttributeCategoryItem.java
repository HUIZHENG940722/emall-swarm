package com.ethan.mall.admin.domain;

import com.ethan.mall.model.PmsProductAttribute;
import com.ethan.mall.model.PmsProductAttributeCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author ethan
 * @Date 8:57 AM 2021/12/19
 * @Description 商品类型分类及相应类型层级结构信息
 */
@Data
public class PmsProductAttributeCategoryItem extends PmsProductAttributeCategory {
    @ApiModelProperty(value = "商品属性列表")
    private List<PmsProductAttribute> productAttributeList;
}
