//package com.ethan.mall.security.filter;
//
//import com.ethan.mall.security.component.JwtTokenUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.annotation.Resource;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @author ethan
// * @Date 8:04 PM 2021/12/29
// * @Description jwt token校验器：用于用户名和密码校验之前
// */
//public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
//    public static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
//    @Value(value = "${jwt.tokenHeader}")
//    private String tokenHeader;
//    @Value(value = "${jwt.tokenHead}")
//    private String tokenHead;
//    @Resource
//    private JwtTokenUtil jwtTokenUtil;
//    @Resource
//    private UserDetailsService userDetailsService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//        String authHeader = request.getHeader(this.tokenHeader);
//        if (authHeader != null && authHeader.startsWith(this.tokenHead)) {
//            String authToken = authHeader.substring(this.tokenHead.length());
//            String username = jwtTokenUtil.getUsernameFromToken(authToken);
//            LOGGER.info("checking username:{}", username);
//            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
//                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                            userDetails, null, userDetails.getAuthorities());
//                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    LOGGER.info("authenticated user:{}", username);
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                }
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
//}
