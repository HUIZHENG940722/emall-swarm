package com.ethan.mall.admin.domain;

import com.ethan.mall.model.PmsProductCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author ethan
 * @Date 9:43 AM 2021/12/5
 * @Description 商品分类层级结构实体
 */
@Data
public class PmsProductCategoryWithChildrenItem extends PmsProductCategory {
    @ApiModelProperty("子级分类")
    private List<PmsProductCategory> children;
}
