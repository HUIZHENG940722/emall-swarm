package com.ethan.mall.admin.controller;

import com.ethan.mall.admin.service.IPmsProductAttributeCategoryService;
import com.ethan.mall.common.api.CommonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ethan
 * @Date 1:57 PM 2021/12/11
 * @Description 商品属性分类管理
 */
@RestController
@Api(tags = "PmsProductAttributeCategoryController", description = "商品属性分类管理")
@RequestMapping("/productAttribute/category")
public class PmsProductAttributeCategoryController {
    @Resource
    private IPmsProductAttributeCategoryService productAttributeCategoryService;
    @ApiOperation("添加商品属性分类")
    @PostMapping(value = "/create")
    public CommonData create(@RequestParam String name) {
        int count = productAttributeCategoryService.create(name);
        if (count > 0) {
            return CommonData.success(count);
        } else {
            return CommonData.failed();
        }
    }
}
