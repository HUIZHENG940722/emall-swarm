package com.ethan.mall.admin.controller;

import com.ethan.mall.admin.domain.OssCallbackData;
import com.ethan.mall.admin.domain.OssPostUploadData;
import com.ethan.mall.admin.service.IOssService;
import com.ethan.mall.common.api.CommonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ethan
 * @Date 2:13 PM 2021/12/12
 * @Description 阿里云Oss对象管理
 */
@RestController
@Api(tags = "OssController", description = "Oss管理")
@RequestMapping("/aliyun/oss")
public class OssController {
    @Resource
    private IOssService ossService;

    @ApiOperation(value = "初始化PostObject方式上传的参数")
    @GetMapping(value = "/initPostData")
    public CommonData<OssPostUploadData> initPostData() {
        OssPostUploadData ossPostUploadData = ossService.initPostData();
        if (ossPostUploadData != null) {
            return CommonData.success(ossPostUploadData);
        }
        return CommonData.failed();
    }

    @ApiOperation(value = "oss上传成功回调")
    @PostMapping(value = "/callback")
    public CommonData<OssCallbackData> callback(HttpServletRequest request) {
        OssCallbackData ossCallbackResult = ossService.callback(request);
        return CommonData.success(ossCallbackResult);
    }
}
