package com.ethan.mall.admin.controller;

import com.ethan.mall.admin.domain.PmsProductCategoryWithChildrenItem;
import com.ethan.mall.admin.service.IPmsProductCategoryService;
import com.ethan.mall.common.api.CommonData;
import com.ethan.mall.common.api.CommonPage;
import com.ethan.mall.model.PmsProductCategory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
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
}