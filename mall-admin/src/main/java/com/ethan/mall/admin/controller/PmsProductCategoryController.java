package com.ethan.mall.admin.controller;

import com.ethan.mall.admin.domain.PmsProductCategoryAddParam;
import com.ethan.mall.admin.domain.PmsProductCategoryWithChildrenItem;
import com.ethan.mall.admin.service.IPmsProductCategoryService;
import com.ethan.mall.common.api.CommonData;
import com.ethan.mall.common.api.CommonPage;
import com.ethan.mall.model.PmsProductCategory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ethan
 * @Date 10:10 下午 2021/12/1
 * @Description 商品分类管理
 */
@RestController
//@CrossOrigin
@Api(value = "PmsProductCategoryController", description = "商品分类管理")
@RequestMapping(value = "/productCategory")
public class PmsProductCategoryController {
    @Resource
    private IPmsProductCategoryService productCategoryService;

    @ApiOperation("分页查询商品分类")
    @GetMapping(value = "/list/{parentId}")
    public CommonData<CommonPage<PmsProductCategory>> getList(@PathVariable Long parentId,
                                                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        List<PmsProductCategory> productCategoryList = productCategoryService.getList(parentId, pageSize, pageNum);
        return CommonData.success(CommonPage.restPage(productCategoryList));
    }

    @ApiOperation("查询所有一级分类以及子分类")
    @GetMapping(value = "/list/withChildren")
    public CommonData<List<PmsProductCategoryWithChildrenItem>> listWithChildren() {
        List<PmsProductCategoryWithChildrenItem> productCategoryWithChildrenItemList =
                productCategoryService.listWithChildren();
        return CommonData.success(productCategoryWithChildrenItemList);
    }

    @ApiOperation(value = "创建商品分类")
    @PostMapping(value = "/create")
    public CommonData add(@Validated @RequestBody PmsProductCategoryAddParam productCategoryAddParam) {
        int count = productCategoryService.create(productCategoryAddParam);
        if (count > 0) {
            return CommonData.success(count);
        } else {
            return CommonData.failed();
        }
    }

    @ApiOperation(value = "获取商品分类")
    @GetMapping(value = "/{id}")
    public CommonData<PmsProductCategory> get(@PathVariable Long id) {
        PmsProductCategory productCategory = productCategoryService.get(id);
        return CommonData.success(productCategory);
    }

    @ApiOperation(value = "更新商品分类")
    @PutMapping(value = "/update/{id}")
    public CommonData update(@PathVariable Long id,
                             @Validated @RequestBody PmsProductCategoryAddParam productCategoryAddParam) {
        int count = productCategoryService.update(id, productCategoryAddParam);
        if (count > 0) {
            return CommonData.success(count);
        }
        return CommonData.failed();
    }
}
