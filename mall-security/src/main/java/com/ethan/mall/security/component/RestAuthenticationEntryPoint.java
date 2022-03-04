//package com.ethan.mall.security.component;
//
//import cn.hutool.json.JSONUtil;
//import com.ethan.mall.common.api.CommonData;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @author ethan
// * @Date 8:06 AM 2021/12/31
// * @Description 当未登录或者token失效访问接口时，自定义的返回结果
// */
//@Component
//public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
//
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response,
//                         AuthenticationException authException) throws IOException, ServletException {
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json");
//        response.getWriter().print(JSONUtil.parse(CommonData.unauthorized(authException.getMessage())));
//        response.getWriter().flush();
//    }
//}
