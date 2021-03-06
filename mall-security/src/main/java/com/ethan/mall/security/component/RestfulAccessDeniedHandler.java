//package com.ethan.mall.security.component;
//
//import cn.hutool.json.JSONUtil;
//import com.ethan.mall.common.api.CommonData;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @author ethan
// * @Date 8:02 AM 2021/12/31
// * @Description 当访问接口没有权限时，自定义的返回结果
// */
//@Component
//public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
//
//    @Override
//    public void handle(HttpServletRequest request, HttpServletResponse response,
//                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json");
//        response.getWriter().println(JSONUtil.parse(CommonData.forbidden(accessDeniedException.getMessage())));
//        response.getWriter().flush();
//    }
//}
