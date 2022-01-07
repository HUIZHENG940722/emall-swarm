package com.ethan.mall.admin.service.feign;

import com.ethan.mall.common.api.CommonData;
import com.ethan.mall.common.domain.Oauth2TokenDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author ethan
 * @Date 10:33 PM 2022/1/7
 * @Description 认证业务类
 */
@FeignClient(value = "mall-auth")
public interface IAuthService {
    @PostMapping(value = "/oauth/token")
    @ApiOperation(value = "oauth2认证获取token信息")
    CommonData<Oauth2TokenDto> getAccessToken(@RequestParam Map<String, String> parameters);
}
