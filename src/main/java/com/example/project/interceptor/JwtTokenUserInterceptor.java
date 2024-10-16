package com.example.project.interceptor;

import com.example.project.common.context.BaseContext;
import com.example.project.common.properties.JwtProperties;
import com.example.project.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * JwtTokenUserInterceptor jwt token拦截器
 * 用于验证微信小程序用户是否登录
 */
@Component // 控制反转，将该类交给spring容器管理
@Slf4j // 日志
public class JwtTokenUserInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtProperties jwtProperties;// jwt配置

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) { //
            return true;
        }

        // 从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getUserTokenName());

        // 校验令牌
        try {
            log.info("jwt校验:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);
            // 为线程局部变量设置id
            BaseContext.setCurrentMap(claims);
            return true;
        } catch (Exception ex) {
            // 令牌解析的时候遇到异常，不通过，返回401状态码
            response.setStatus(401);
            return false;
        }
    }
}
