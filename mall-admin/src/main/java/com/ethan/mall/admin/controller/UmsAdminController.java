package com.ethan.mall.admin.controller;

import com.ethan.mall.admin.domain.UmsAdminLoginParam;
import com.ethan.mall.admin.domain.UmsAdminRegisterParam;
import com.ethan.mall.admin.service.IUmsAdminService;
import com.ethan.mall.common.api.CommonData;
import com.ethan.mall.common.api.CommonPage;
import com.ethan.mall.common.domain.LoginUser;
import com.ethan.mall.model.UmsAdmin;
import com.ethan.mall.model.UmsRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;


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
    public CommonData<CommonPage<UmsAdmin>> getList(@RequestParam(value = "keyword", required = false) String keyword,
                                                    @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsAdmin> adminList = adminService.getList(keyword, pageSize, pageNum);
        return CommonData.success(CommonPage.restPage(adminList));
    }

    @ApiOperation(value = "修改指定用户信息")
    @PutMapping(value = "/update/{id}")
    public CommonData<Integer> update(@PathVariable Long id, @Validated @RequestBody UmsAdminRegisterParam adminRegisterParam) {
        int count = adminService.update(id, adminRegisterParam);
        if (count > 0) {
            return CommonData.success(count);
        }
        return CommonData.failed();
    }

    @ApiOperation(value = "根据用户名获取登录用户信息")
    @GetMapping(value = "/loadByUsername")
    public LoginUser loadUserByUsername(@RequestParam(value = "username") String username) {
        LoginUser loginUser = adminService.loadUserByUsername(username);
        return loginUser;
    }

    @ApiOperation(value = "登录以后返回token")
    @PostMapping(value = "/login")
    public CommonData login(@Validated @RequestBody UmsAdminLoginParam adminLoginParam) {
        return adminService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword());
    }

    @ApiOperation(value = "登出功能")
    @PostMapping(value = "/logout")
    public CommonData logout() {
        return CommonData.success(null);
    }

    @ApiOperation(value = "给用户分配角色列表")
    @PostMapping(value = "/role/update")
    public CommonData<Integer> updateRole(@RequestParam Long adminId, @RequestParam List<Long> roleIds) {
        int count = adminService.updateRole(adminId, roleIds);
        return CommonData.success(count);
    }

    @ApiOperation(value = "获取指定用户角色")
    @GetMapping(value = "/role/{adminId}")
    public CommonData<List<UmsRole>> getRoleList(@PathVariable Long adminId) {
        List<UmsRole> roleList = adminService.getRoleList(adminId);
        return CommonData.success(roleList);
    }

    /*@ApiOperation(value = "获取当前登录用户信息")
    @GetMapping(value = "/info")
    public CommonData<Map<String, Object>> getAdminInfo(Principal principal) {
        Map<String, Object> data = adminService.getAdminInfo(principal);
        if (MapUtil.isEmpty(data)) {
            CommonData.unauthorized(null);
        }
        return CommonData.success(data);
    }*/
}
