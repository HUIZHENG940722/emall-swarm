package com.ethan.mall.admin.service;

import com.ethan.mall.admin.domain.OssCallbackData;
import com.ethan.mall.admin.domain.OssPostUploadData;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ethan
 * @Date 3:14 PM 2021/12/12
 * @Description 阿里云Oss对象管理业务接口
 */
public interface IOssService {
    /**
     * 初始化阿里云OSS存储Post方式上传参数
     * @return
     */
    OssPostUploadData initPostData();

    /**
     * OSS上传回调
     * @param request
     * @return
     */
    OssCallbackData callback(HttpServletRequest request);
}
