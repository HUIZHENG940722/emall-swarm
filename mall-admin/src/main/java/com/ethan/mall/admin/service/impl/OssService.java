package com.ethan.mall.admin.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.ethan.mall.admin.domain.OssPostUploadData;
import com.ethan.mall.admin.service.IOssService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
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

    @Resource
    private OSSClient ossClient;

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
        // 2.3 初始化policy
        PolicyConditions policyConditions = new PolicyConditions();
        // 文件大小
        long maxSize = ALIYUN_OSS_MAX_SIZE * 1024 * 1024;
        policyConditions.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, maxSize);
        // 设置文件路径规范
        policyConditions.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
        // 签名有效期
        long expireEndTime = System.currentTimeMillis() + ALIYUN_OSS_EXPIRE * 1000;
        Date expiration = new Date(expireEndTime);
        String postPolicy = ossClient.generatePostPolicy(expiration, policyConditions);
        byte[] bytes = postPolicy.getBytes(StandardCharsets.UTF_8);
        String policy = BinaryUtil.toBase64String(bytes);
        ossPostUploadData.setPolicy(policy);
        String signature = ossClient.calculatePostSignature(policy);
        ossPostUploadData.setSignature(signature);
        // 2.4 设置OSSAccessKeyId
        ossPostUploadData.setOSSAccessKeyId(ossClient.getCredentialsProvider().getCredentials().getAccessKeyId());
        // 3 返回结果集
        return ossPostUploadData;
    }
}
