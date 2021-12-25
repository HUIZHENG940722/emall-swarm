package com.ethan.mall.admin.controller;

import com.ethan.mall.admin.service.ICmsSubjectService;
import com.ethan.mall.common.api.CommonData;
import com.ethan.mall.model.CmsSubject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ethan
 * @Date 9:06 PM 2021/12/25
 * @Description 商品专题管理
 */
@RestController
@RequestMapping(value = "/subject")
@Api(tags = "CmsSubjectController", description = "商品专题管理")
public class CmsSubjectController {
    @Resource
    private ICmsSubjectService subjectService;

    @ApiOperation(value = "获取全部商品专题")
    @GetMapping(value = "/listAll")
    public CommonData<List<CmsSubject>> listAll() {
        List<CmsSubject> subjectList = subjectService.listAll();
        return CommonData.success(subjectList);
    }
}
