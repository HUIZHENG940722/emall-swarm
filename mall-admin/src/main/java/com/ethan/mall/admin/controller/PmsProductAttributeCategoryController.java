package com.ethan.mall.admin.controller;

import com.ethan.mall.admin.service.IPmsProductAttributeCategoryService;
import com.ethan.mall.common.api.CommonData;
import com.ethan.mall.common.api.CommonPage;
import com.ethan.mall.model.PmsProductAttributeCategory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @ApiOperation("分页获取所有商品属性分类")
    @GetMapping(value = "/list")
    public CommonData<CommonPage<PmsProductAttributeCategory>> getList(
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "1") Integer pageNum) {
        List<PmsProductAttributeCategory> productAttributeCategoryList = productAttributeCategoryService
                .getList(pageSize, pageNum);
        return CommonData.success(CommonPage.restPage(productAttributeCategoryList));
    }
}
