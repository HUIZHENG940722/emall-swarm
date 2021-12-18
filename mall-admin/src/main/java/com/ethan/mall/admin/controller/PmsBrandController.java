package com.ethan.mall.admin.controller;

import com.ethan.mall.admin.domain.PmsBrandCreateParam;
import com.ethan.mall.admin.service.IPmsBrandService;
import com.ethan.mall.common.api.CommonData;
import com.ethan.mall.common.api.CommonPage;
import com.ethan.mall.model.PmsBrand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ethan
 * @Date 7:22 PM 2021/12/5
 * @Description 商品品牌管理
 */
@RestController
@Api(tags = "PmsBrandController", description = "商品品牌管理")
@RequestMapping(value = "/brand")
public class PmsBrandController {
    @Resource
    private IPmsBrandService brandService;
    @ApiOperation(value = "根据品牌名称分页获取品牌列表")
    @GetMapping(value = "/list")
    public CommonData<CommonPage<PmsBrand>> getList(@RequestParam(value = "keyword", required = false) String keyword,
                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<PmsBrand> brandList = brandService.getList(keyword, pageNum, pageSize);
        return CommonData.success(CommonPage.restPage(brandList));
    }

    @ApiOperation(value = "创建商品品牌")
    @PostMapping(value = "/create")
    public CommonData create(@Validated @RequestBody PmsBrandCreateParam pmsBrand) {
        int count = brandService.create(pmsBrand);
        if (count > 0) {
            return CommonData.success(count);
        }
        return CommonData.failed();
    }
}
