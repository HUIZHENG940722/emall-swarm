package com.ethan.mall.admin.controller;

import com.ethan.mall.admin.domain.PmsProductQueryParam;
import com.ethan.mall.admin.service.IPmsProductService;
import com.ethan.mall.common.api.CommonData;
import com.ethan.mall.common.api.CommonPage;
import com.ethan.mall.model.PmsProduct;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ethan
 * @Date 9:51 PM 2021/12/4
 * @Description 商品管理
 */
@RestController
public class PmsProductController {
    @Resource
    private IPmsProductService productService;

    @ApiOperation("分页多条件查询商品")
    @GetMapping(value = "/list")
    public CommonData<CommonPage<PmsProduct>> getList(PmsProductQueryParam productQueryParam,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<PmsProduct> productList = productService.list(productQueryParam, pageSize, pageNum);
        return CommonData.success(CommonPage.restPage(productList));
    }

    @ApiOperation(value = "添加商品")
    @PostMapping(value = "/add")
    public CommonData add() {
        return null;
    }
}
