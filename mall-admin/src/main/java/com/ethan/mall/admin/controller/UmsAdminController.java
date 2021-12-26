package com.ethan.mall.admin.controller;

import com.ethan.mall.admin.domain.UmsAdminRegisterParam;
import com.ethan.mall.admin.service.IUmsAdminService;
import com.ethan.mall.common.api.CommonData;
import com.ethan.mall.model.UmsAdmin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ethan
 * @Date 7:25 下午 2021/11/27
 * @Description 后台用户管理
 */
@RestController
@RequestMapping(value = "/admin")
@Api(tags = "UmsAdminController", description = "后台用户管理")
public class UmsAdminController {
    @Resource
    private IUmsAdminService adminService;

    @ApiOperation(value = "注册用户")
    @PostMapping(value = "/register")
    public CommonData<UmsAdmin> register(@Validated @RequestBody UmsAdminRegisterParam adminRegisterParam) {
        UmsAdmin admin = adminService.register(adminRegisterParam);
        if (admin == null) {
            return CommonData.failed();
        }
        return CommonData.success(admin);
    }

    @ApiOperation(value = "名称分页查询用户列表")
    @GetMapping(value = "/list")
    public CommonData<List<UmsAdmin>> getList(@RequestParam(value = "keyword", required = false) String keyword,
                                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsAdmin> adminList = adminService.getList(keyword, pageSize, pageNum);
        return CommonData.success(adminList);
    }
}
