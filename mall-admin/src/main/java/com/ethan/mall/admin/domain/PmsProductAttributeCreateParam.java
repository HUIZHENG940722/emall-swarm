package com.ethan.mall.admin.domain;

import com.ethan.mall.admin.validator.FlagValidator;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author ethan
 * @Date 6:53 PM 2021/12/11
 * @Description 商品类型添加参数
 */
@Data
public class PmsProductAttributeCreateParam {
    @NotEmpty
    @ApiModelProperty("属性分类ID")
    private Long productAttributeCategoryId;
    @NotEmpty
    @ApiModelProperty("属性名称")
    private String name;
    @FlagValidator(values = {"0", "1", "2"})
    @ApiModelProperty("属性选择类型：0->唯一；1->单选；2->多选")
    private Integer selectType;
    @FlagValidator(values = {"0", "1"})
    @ApiModelProperty("属性录入方式：0->手工录入；1->从列表中选取")
    private Integer inputType;
    @ApiModelProperty("可选值列表，以逗号隔开")
    private String inputList;
    private Integer sort;
    @ApiModelProperty("分类筛选样式：0->普通；1->颜色")
    @FlagValidator(values = {"0","1"})
    private Integer filterType;
    @FlagValidator(values = {"0", "1", "2"})
    @ApiModelProperty("检索类型；0->不需要进行检索；1->关键字检索；2->范围检索")
    private Integer searchType;
    @FlagValidator(values = {"0", "1"})
    @ApiModelProperty("相同属性产品是否关联；0->不关联；1->关联")
    private Integer relatedStatus;
    @FlagValidator(values = {"0", "1"})
    @ApiModelProperty("是否支持手动新增；0->不支持；1->支持")
    private Integer handAddStatus;
    @FlagValidator(values = {"0", "1"})
    @ApiModelProperty("属性的类型；0->规格；1->参数")
    private Integer type;
}
