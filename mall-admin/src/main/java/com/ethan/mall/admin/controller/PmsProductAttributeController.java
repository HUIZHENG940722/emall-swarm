package com.ethan.mall.admin.controller;

import com.ethan.mall.admin.domain.PmsProductAttributeCreateParam;
import com.ethan.mall.admin.service.IPmsProductAttributeService;
import com.ethan.mall.common.api.CommonData;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ethan
 * @Date 6:49 PM 2021/12/11
 * @Description 商品类型管理
 */
@RestController
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
}
