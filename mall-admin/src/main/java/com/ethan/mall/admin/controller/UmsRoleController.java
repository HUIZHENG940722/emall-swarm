package com.ethan.mall.admin.controller;

import com.ethan.mall.admin.service.IUmsRoleService;
import com.ethan.mall.common.api.CommonData;
import com.ethan.mall.common.api.CommonPage;
import com.ethan.mall.model.UmsMenu;
import com.ethan.mall.model.UmsResource;
import com.ethan.mall.model.UmsRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ethan
 * @Date 3:47 PM 2021/12/26
 * @Description 后台用户角色管理
 */
@RestController
@Api(tags = "UmsRoleController", description = "后台用户角色管理")
@RequestMapping(value="/role")
public class UmsRoleController {
    @Resource
    private IUmsRoleService roleService;

    @GetMapping(value="/list")
    @ApiOperation(value = "根据角色名称分页获取角色列表")
    public CommonData<CommonPage<UmsRole>> getList(@RequestParam(value = "keyword", required = false) String keyword,
                                                   @RequestParam(value = "pageNum") Integer pageNum,
                                                   @RequestParam(value = "pageSize") Integer pageSize) {
        List<UmsRole> roleList = roleService.getList(keyword, pageNum, pageSize);
        return CommonData.success(CommonPage.restPage(roleList));
    }

    @ApiOperation(value = "添加角色")
    @PostMapping(value = "/create")
    public CommonData<Integer> create(@RequestBody UmsRole role) {
        int count = roleService.create(role);
        if (count > 0) {
            return CommonData.success(count);
        }
        return CommonData.failed();
    }

    @ApiOperation(value = "更新角色信息")
    @PutMapping(value = "/update/{id}")
    public CommonData<Integer> update(@PathVariable Long id, @RequestBody UmsRole role) {
        int count = roleService.update(id, role);
        if (count > 0) {
            return CommonData.success(count);
        }
        return CommonData.failed();
    }

    @ApiOperation("给角色分配菜单")
    @PostMapping(value = "/allocMenu")
    public CommonData<Integer> allocMenu(@RequestParam Long roleId, @RequestParam List<Long> menuIds) {
        int count = roleService.allocMenu(roleId, menuIds);
        return CommonData.success(count);
    }

    @ApiOperation("获取角色相关菜单")
    @GetMapping(value = "/menuList/{roleId}")
    public CommonData<List<UmsMenu>> menuList(@PathVariable Long roleId) {
        List<UmsMenu> menuList = roleService.menuList(roleId);
        return CommonData.success(menuList);
    }

    @ApiOperation("获取角色相关资源")
    @GetMapping(value = "/listResource/{roleId}")
    public CommonData<List<UmsResource>> listResources(@PathVariable Long roleId) {
        List<UmsResource> resourceList = roleService.listResources(roleId);
        return CommonData.success(resourceList);
    }

    @ApiOperation("给角色分配资源")
    @PostMapping(value = "/allocResource")
    public CommonData<Integer> addResources(@RequestParam Long roleId, @RequestParam List<Long> resourceIds) {
        int count = roleService.allocResource(roleId, resourceIds);
        return CommonData.success(count);
    }

    @ApiOperation(value = "获取所有的角色列表")
    @GetMapping(value = "/listAll")
    public CommonData<List<UmsRole>> listAll() {
        List<UmsRole> roleList = roleService.listAll();
        return CommonData.success(roleList);
    }
}
