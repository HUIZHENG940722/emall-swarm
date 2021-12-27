package com.ethan.mall.admin.service.impl;

import com.ethan.mall.admin.service.IUmsMenuService;
import com.ethan.mall.mapper.UmsMenuMapper;
import com.ethan.mall.model.UmsMenu;
import com.ethan.mall.model.UmsMenuExample;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author ethan
 * @Date 6:56 PM 2021/12/26
 * @Description 后台菜单管理业务类
 */
@Service
public class UmsMenuService implements IUmsMenuService {
    @Resource
    private UmsMenuMapper menuMapper;
    @Override
    public List<UmsMenu> getList(Long parentId, Integer pageSize, Integer pageNum) {
        // 1 校验
        // 2 查询
        // 2.1 开启分页功能
        PageHelper.startPage(pageNum, pageSize);
        // 2.1 拼装查询条件
        UmsMenuExample example = new UmsMenuExample();
        example.setOrderByClause("sort desc");
        example.createCriteria().andParentIdEqualTo(parentId);
        // 2.2 执行查询
        List<UmsMenu> menuList = menuMapper.selectByExample(example);
        // 3 返回结果集
        return menuList;
    }

    @Override
    public int create(UmsMenu menu) {
        // 1 校验
        // 2 创建
        // 2.1 初始化数据
        // 设置创建时间
        menu.setCreatedTime(new Date());
        // 设置菜单层级
        if (menu.getParentId() == 0) {
            menu.setLevel(0);
        } else {
            UmsMenu parentMenu = menuMapper.selectByPrimaryKey(menu.getParentId());
            if (parentMenu != null) {
                menu.setLevel(parentMenu.getLevel() + 1);
            } else {
                menu.setLevel(0);
            }
        }
        // 2.2 执行创建
        int count = menuMapper.insertSelective(menu);
        // 3 返回结果集
        return count;
    }
}
