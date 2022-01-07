//package com.ethan.mall.security.component;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author ethan
// * @Date 8:49 PM 2021/12/29
// * @Description Jwt Token工具类
// */
//@Component
//public class JwtTokenUtil {
//    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
//    private static final String CLAIM_KEY_USERNAME = "sub";
//    private static final String CLAIM_KEY_CREATED = "created";
//    @Value("${jwt.secret}")
//    private String secret;
//    @Value(value = "${jwt.expiration}")
//    private Long expiration;
//
//    /**
//     * 从token中获取JWT中的负载
//     * @param token
//     * @return
//     */
//    private Claims getClaimsFromToken(String token){
//        Claims claims = null;
//        try {
//            claims = Jwts.parser()
//                    .setSigningKey(secret)
//                    .parseClaimsJws(token)
//                    .getBody();
//        } catch (Exception e) {
//            LOGGER.info("JWT格式验证失败:{}",token);
//        }
//        return claims;
//    }
//
//    /**
//     * 根据用户信息生成JWT Token信息
//     * @param userDetails
//     * @return
//     */
//    public String generateToken(UserDetails userDetails) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
//        claims.put(CLAIM_KEY_CREATED, new Date());
//        return Jwts.builder()
//                .setClaims(claims)
//                .setExpiration(new Date(System.currentTimeMillis() + expiration))
//                .signWith(SignatureAlgorithm.HS512, secret)
//                .compact();
//    }
//
//    /**
//     * 从token中获取登录用户名
//     * @param token
//     * @return
//     */
//    public String getUsernameFromToken(String token) {
//        String username = null;
//        try {
//            Claims claims = getClaimsFromToken(token);
//            username = claims.getSubject();
//        } catch (Exception e) {
//            username = null;
//        }
//        return username;
//    }
//
//    /**
//     * 验证token是否还有效
//     * @param token
//     * @param userDetails
//     * @return
//     */
//    public boolean validateToken(String token, UserDetails userDetails) {
//        String username = getUsernameFromToken(token);
//        return username.equals(userDetails.getUsername())
//                && !getExpirationDateFromToken(token).before(new Date());
//    }
//
//    /**
//     * 从token中获取失效时间
//     * @param token
//     * @return
//     */
//    public Date getExpirationDateFromToken(String token) {
//        Date date = null;
//        try {
//            Claims claims = Jwts.parser()
//                    .setSigningKey(secret)
//                    .parseClaimsJws(token)
//                    .getBody();
//            date = claims.getExpiration();
//        } catch (Exception e) {
//            LOGGER.info("JWT格式验证失败：{}", token);
//        }
//        return date;
//    }
//
//    /**
//     * 判断token是否可以被刷新
//     * @param token
//     * @return
//     */
//    public boolean canRefresh(String token) {
//        return !getExpirationDateFromToken(token).before(new Date());
//    }
//
//    /**
//     * 刷新jwt token
//     * @param token
//     * @return
//     */
//    public String refreshToken(String token) {
//        Claims claims = getClaimsFromToken(token);
//        claims.put(CLAIM_KEY_CREATED, new Date());
//        return Jwts.builder()
//                .setClaims(claims)
//                .setExpiration(new Date(System.currentTimeMillis() + expiration))
//                .signWith(SignatureAlgorithm.HS512, secret)
//                .compact();
//    }
//}
