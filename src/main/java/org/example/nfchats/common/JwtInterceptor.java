package org.example.nfchats.common;


import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.example.nfchats.entity.Admin;
import org.example.nfchats.exception.CustomException;
import org.example.nfchats.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * jwt拦截器
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(JwtInterceptor.class);

    @Resource
    private AdminService adminService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        //拦截器取到请求先进行判断，如果是OPTIONS请求，则放行
        if("OPTIONS".equals(request.getMethod().toUpperCase())) {
            System.out.println("Method:OPTIONS");
            return true;
        }

        // 1. 从http请求的header中获取token
        String token = request.getHeader("Authorization");
        if (StrUtil.isBlank(token)) {
            // 如果没拿到，我再去参数里面拿一波试试  /api/admin?token=xxxxx
            token = request.getParameter("Authorization");
        }
        // 2. 开始执行认证
        if (StrUtil.isBlank(token)) {
            String errMsg="无token，请重新登录";
            throw new CustomException(errMsg);
        }
        // 获取 token 中的userId
        String userId;
        Admin admin;
        try {
            userId = JWT.decode(token).getAudience().get(0);
            // 根据token中的userid查询数据库
            admin = adminService.findById(Integer.parseInt(userId));
        } catch (Exception e) {
            String errMsg = "token验证失败，请重新登录";
            log.error(errMsg + ", token=" + token, e);
            throw new CustomException(errMsg);
        }
        if (admin == null) {
            throw new CustomException("用户不存在，请重新登录");
        }
        try {
            // 用户密码加签验证 token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(admin.getPassword())).build();
            jwtVerifier.verify(token); // 验证token
        } catch (JWTVerificationException e) {
            throw new CustomException("token验证失败，请重新登录");
        }
        log.info("token验证成功，允许放行");
        return true;
    }
}