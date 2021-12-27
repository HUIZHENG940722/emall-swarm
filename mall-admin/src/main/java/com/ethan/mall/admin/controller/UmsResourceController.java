package com.ethan.mall.admin.controller;

import com.ethan.mall.admin.service.IUmsResourceService;
import com.ethan.mall.common.api.CommonData;
import com.ethan.mall.common.api.CommonPage;
import com.ethan.mall.model.UmsResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ethan
 * @Date 9:27 PM 2021/12/27
 * @Description 后台资源管理
 */
@RestController
@Api(tags = "UmsResourceController", description = "后台资源管理")
@RequestMapping(value = "/resource")
public class UmsResourceController {
    @Resource
    private IUmsResourceService resourceService;

    @ApiOperation(value = "创建后台资源")
    @PostMapping(value = "/create")
    public CommonData<Integer> create(@RequestBody UmsResource umsResource) {
        int count = resourceService.create(umsResource);
        if (count > 0) {
            return CommonData.success(count);
        }
        return CommonData.failed();
    }

    @ApiOperation(value = "分页模糊查询所有的资源")
    @GetMapping(value = "/list")
    public CommonData<CommonPage<UmsResource>> getList(@RequestParam(required = false) Long categoryId,
                                                       @RequestParam(required = false) String nameKeyword,
                                                       @RequestParam(required = false) String urlKeyword,
                                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsResource> resourceList = resourceService.getList(categoryId, nameKeyword, urlKeyword, pageSize, pageNum);
        return CommonData.success(CommonPage.restPage(resourceList));
    }
}
