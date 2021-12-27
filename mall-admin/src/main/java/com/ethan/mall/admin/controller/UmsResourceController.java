package com.ethan.mall.admin.controller;

import com.ethan.mall.admin.service.IUmsResourceService;
import com.ethan.mall.common.api.CommonData;
import com.ethan.mall.model.UmsResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
}
