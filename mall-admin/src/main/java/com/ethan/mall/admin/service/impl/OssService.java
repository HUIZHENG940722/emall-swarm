package com.ethan.mall.admin.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.URLUtil;
import cn.hutool.crypto.BCUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.AsymmetricAlgorithm;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.digest.DigestAlgorithm;
import com.aliyun.oss.common.auth.ServiceSignature;
import com.ethan.mall.admin.domain.OssCallbackData;
import com.ethan.mall.admin.domain.OssPostUploadData;
import com.ethan.mall.admin.service.IOssService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Signature;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ethan
 * @Date 3:14 PM 2021/12/12
 * @Description 阿里云Oss对象管理业务类
 */
@Service
public class OssService implements IOssService {
    @Value("${aliyun.oss.bucketName}")
    private String ALIYUN_OSS_BUCKET_NAME;
    @Value("${aliyun.oss.endpoint}")
    private String ALIYUN_OSS_ENDPOINT;
    @Value("${aliyun.oss.dir.prefix}")
    private String ALIYUN_OSS_DIR_PREFIX;
    @Value("${aliyun.oss.policy.expire}")
    private int ALIYUN_OSS_EXPIRE;
    @Value("${aliyun.oss.maxSize}")
    private int ALIYUN_OSS_MAX_SIZE;
    @Value("${aliyun.oss.accessKeyId}")
    private String ALIYUN_OSS_ACCESSKEYID;
    @Value("${aliyun.oss.accessKeySecret}")
    private String ALIYUN_OSS_ACCESSKEYSECRET;
    @Value("${aliyun.oss.callback}")
    private String ALIYUN_OSS_CALLBACK;

    @Override
    public OssPostUploadData initPostData() {
        // 1 校验
        // 2 执行相应的业务逻辑
        OssPostUploadData ossPostUploadData = new OssPostUploadData();
        // 2.1 初始化文件夹路径
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dir = ALIYUN_OSS_DIR_PREFIX + sdf.format(new Date());
        ossPostUploadData.setDir(dir);
        // 2.2 初始化提交节点
        String action = "https://" + ALIYUN_OSS_BUCKET_NAME + "." + ALIYUN_OSS_ENDPOINT;
        ossPostUploadData.setHost(action);
        // 2.3 上传之后的回调设置
        String callbackJson = "{\"callbackUrl\":\"" + ALIYUN_OSS_CALLBACK + "\",\"callbackBody\":\"filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}\"}";
        String callback = Base64.encode(callbackJson);
        ossPostUploadData.setCallback(callback);
        // 2.4 设置OSSAccessKeyId
        ossPostUploadData.setOSSAccessKeyId(ALIYUN_OSS_ACCESSKEYID);
        // 2.5 初始化policy
        // 文件大小
        long maxSize = ALIYUN_OSS_MAX_SIZE * 1024 * 1024;
        // 设置文件路径规范
        // 签名有效期
        long expireEndTime = System.currentTimeMillis() + ALIYUN_OSS_EXPIRE * 1000;
        // 回调签名
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date expiration = new Date(expireEndTime);
        String format = dateFormat.format(expiration);
        String policyStr = "{\"expiration\":\"" + format + "\",\"conditions\":[[\"content-length-range\",0,"
                + maxSize + "],{\"bucket\":\"" + ALIYUN_OSS_BUCKET_NAME + "\"},[\"starts-with\",\"$key\",\""
                + dir + "\"],{\"callback\":\"" + callback + "\"}]}";
        String policy = Base64.encode(policyStr);
        ossPostUploadData.setPolicy(policy);
        // 2.6 生成签名信息
        String signature = ServiceSignature.create().computeSignature(ALIYUN_OSS_ACCESSKEYSECRET, policy);
        ossPostUploadData.setSignature(signature);
        // 3 返回结果集
        return ossPostUploadData;
    }

    @Override
    public OssCallbackData callback(HttpServletRequest request) {
        // 1 校验
        // 2 执行逻辑
        OssCallbackData ossCallbackData = new OssCallbackData();
        // 2.1 获取文件名
        String filename = request.getParameter("filename");
        filename = "https://".concat(ALIYUN_OSS_BUCKET_NAME).concat(".").concat(ALIYUN_OSS_ENDPOINT)
                .concat("/").concat(filename);
        ossCallbackData.setFilename(filename);
        ossCallbackData.setSize(request.getParameter("size"));
        ossCallbackData.setMimeType(request.getParameter("mimeType"));
        ossCallbackData.setWidth(request.getParameter("width"));
        ossCallbackData.setHeight(request.getParameter("height"));
        // 3 返回结果集
        return ossCallbackData;
    }
}
