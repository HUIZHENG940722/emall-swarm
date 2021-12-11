package com.ethan.mall.admin.controller;

import com.ethan.mall.admin.domain.PmsProductAttributeCreateParam;
import com.ethan.mall.admin.service.IPmsProductAttributeService;
import com.ethan.mall.common.api.CommonData;
import com.ethan.mall.common.api.CommonPage;
import com.ethan.mall.model.PmsProductAttribute;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ethan
 * @Date 6:49 PM 2021/12/11
 * @Description 商品类型管理
 */
@RestController
@Api(tags = "PmsProductAttributeController", description = "商品属性管理")
@RequestMapping("/productAttribute")
public class PmsProductAttributeController {
    @Resource
    private IPmsProductAttributeService productAttributeService;
    @ApiOperation("添加商品类型信息")
    @PostMapping(value = "/create")
    public CommonData create(@RequestBody PmsProductAttributeCreateParam productAttributeCreateParam) {
        int count = productAttributeService.create(productAttributeCreateParam);
        if (count > 0) {
            return CommonData.success(count);
        } else {
            return CommonData.failed();
        }
    }

    @ApiOperation("根据类型分类分页查询类型列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "0表示属性，1表示参数", required = true, paramType = "query", dataType = "integer")})
    @GetMapping(value = "/list/{cid}")
    @ResponseBody
    public CommonData<CommonPage<PmsProductAttribute>> getList(@PathVariable Long cid,
                                                                 @RequestParam(value = "type") Integer type,
                                                                 @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<PmsProductAttribute> productAttributeList = productAttributeService.getList(cid, type, pageSize, pageNum);
        return CommonData.success(CommonPage.restPage(productAttributeList));
    }

    @ApiOperation("查询单个商品属性")
    @GetMapping(value = "/{id}")
    @ResponseBody
    public CommonData<PmsProductAttribute> getItem(@PathVariable Long id) {
        PmsProductAttribute productAttribute = productAttributeService.getItem(id);
        return CommonData.success(productAttribute);
    }

    @ApiOperation("修改商品属性信息")
    @PostMapping(value = "/update/{id}")
    public CommonData update(@PathVariable Long id, @RequestBody PmsProductAttributeCreateParam productAttributeCreateParam) {
        int count = productAttributeService.update(id, productAttributeCreateParam);
        if (count > 0) {
            return CommonData.success(count);
        } else {
            return CommonData.failed();
        }
    }
}
