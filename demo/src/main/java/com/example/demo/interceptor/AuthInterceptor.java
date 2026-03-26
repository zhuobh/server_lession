package com.example.demo.interceptor;

import com.example.demo.common.ResultCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        String uri = request.getRequestURI();

        // 精细放行：POST 注册 / GET 查询用户
        boolean isCreateUser = "POST".equalsIgnoreCase(method) && "/api/users".equals(uri);
        boolean isGetUser = "GET".equalsIgnoreCase(method) && uri.startsWith("/api/users/");

        if (isCreateUser || isGetUser) {
            return true;
        }

        // Token 校验
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            response.setContentType("application/json;charset=UTF-8");
            String json = String.format(
                    "{\"code\":%d,\"msg\":\"%s\"}",
                    ResultCode.TOKEN_INVALID.getCode(),
                    ResultCode.TOKEN_INVALID.getMsg()
            );
            response.getWriter().write(json);
            return false;
        }
        return true;
    }
}