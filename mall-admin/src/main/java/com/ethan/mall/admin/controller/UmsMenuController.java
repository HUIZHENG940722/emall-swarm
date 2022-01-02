package com.ethan.mall.admin.controller;

import com.ethan.mall.admin.domain.UmsMenuNode;
import com.ethan.mall.admin.service.IUmsMenuService;
import com.ethan.mall.common.api.CommonData;
import com.ethan.mall.common.api.CommonPage;
import com.ethan.mall.model.UmsMenu;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ethan
 * @Date 6:14 PM 2021/12/26
 * @Description 后台菜单管理
 */
@RestController
@RequestMapping(value = "/menu")
@Api(tags = "UmsMenuController", description = "后台菜单管理")
public class UmsMenuController {
    @Resource
    private IUmsMenuService menuService;
    @ApiOperation(value = "分页查询后台菜单")
    @GetMapping(value = "/list/{parentId}")
    public CommonData<CommonPage<UmsMenu>> getList(@PathVariable Long parentId,
                                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsMenu> menuList = menuService.getList(parentId, pageSize, pageNum);
        return CommonData.success(CommonPage.restPage(menuList));
    }

    @ApiOperation(value = "创建菜单")
    @PostMapping(value = "/create")
    public CommonData<Integer> create(@RequestBody UmsMenu menu) {
        int count = menuService.create(menu);
        if (count > 0) {
            return CommonData.success(count);
        }
        return CommonData.failed();
    }

    @ApiOperation(value = "获取所有菜单的树形结构列表")
    @GetMapping(value = "/treeList")
    public CommonData<List<UmsMenuNode>> treeList() {
        List<UmsMenuNode> list = menuService.treeList();
        return CommonData.success(list);
    }
}
