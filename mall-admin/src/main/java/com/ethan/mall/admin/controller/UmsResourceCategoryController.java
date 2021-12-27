package com.ethan.mall.admin.controller;

import com.ethan.mall.admin.service.IUmsResourceCategoryService;
import com.ethan.mall.common.api.CommonData;
import com.ethan.mall.model.UmsResourceCategory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.A;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ethan
 * @Date 8:25 AM 2021/12/27
 * @Description 后台资源分类管理
 */
@RestController
@Api(tags = "UmsResourceCategoryController", description = "后台资源分类管理")
@RequestMapping(value = "/resourceCategory")
public class UmsResourceCategoryController {
    @Resource
    private IUmsResourceCategoryService resourceCategoryService;

    @PostMapping(value = "/create")
    @ApiOperation(value = "创建资源分类")
    public CommonData create(@RequestBody UmsResourceCategory resourceCategory) {
        int count = resourceCategoryService.create(resourceCategory);
        if (count > 0) {
            return CommonData.success(count);
        }
        return CommonData.failed();
    }

    @ApiOperation(value = "查询所有的资源分类")
    @GetMapping(value = "/listAll")
    public CommonData<List<UmsResourceCategory>> getListAll() {
        List<UmsResourceCategory> resourceCategoryList = resourceCategoryService.getListAll();
        return CommonData.success(resourceCategoryList);
    }

    @ApiOperation(value = "更新指定资源分类")
    @PutMapping(value = "/update/{id}")
    public CommonData<Integer> update(@PathVariable Long id, @RequestBody UmsResourceCategory resource) {
        int count = resourceCategoryService.update(id, resource);
        if (count > 0) {
            return CommonData.success(count);
        }
        return CommonData.failed();
    }
}
