package com.ethan.mall.admin.domain;

import com.ethan.mall.model.UmsMenu;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author ethan
 * @Date 11:50 AM 2022/1/2
 * @Description 后台菜单树形结构模型
 */
@Data
public class UmsMenuNode extends UmsMenu {
    @ApiModelProperty(value = "子级菜单")
    private List<UmsMenuNode> children;
}
