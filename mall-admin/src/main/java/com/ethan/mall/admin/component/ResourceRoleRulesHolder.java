package com.ethan.mall.admin.component;

import com.ethan.mall.admin.service.IUmsResourceService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author ethan
 * @Date 7:31 PM 2022/1/17
 * @Description 资源与角色访问对应关系操作组件
 */
@Component
public class ResourceRoleRulesHolder {
    @Resource
    private IUmsResourceService resourceService;

    @PostConstruct
    public void initResourceRolesMap(){
        resourceService.initResourceRolesMap();
    }
}
