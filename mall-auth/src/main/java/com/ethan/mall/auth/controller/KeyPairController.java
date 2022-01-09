package com.ethan.mall.auth.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * @author ethan
 * @Date 5:05 PM 2022/1/9
 * @Description RSA公钥管理
 */
@RestController
@Api(tags = "KeyPairController", description = "RSA公钥管理")
public class KeyPairController {
    @Resource
    private KeyPair keyPair;

    @GetMapping(value = "/rsa/publicKey")
    @ApiOperation(value = "获取RSA公钥接口")
    public Map<String, Object> getKeyPair() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }
}
