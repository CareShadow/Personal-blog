package com.blog.Interceptor;

import com.blog.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @ClassName LoginInterceptor
 * @Description TODO
 * @Author admin
 * @Date 2022/4/6 15:48
 * @Version 1.0
 **/
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 简单的白名单，登录这个接口直接放行
        if ("/admin/login".equals(request.getRequestURI())) {
            return true;
        }

        // 从请求头中获取token字符串并解析
        Claims claims = JwtUtils.parse(request.getHeader("Authorization"));
        // 已登录就直接放行
        if (claims != null) {
            return true;
        }

        // 走到这里就代表是其他接口，且没有登录
        // 设置响应数据类型为json（前后端分离）
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        // 设置响应内容，结束请求
        out.write("请先登录");
        out.flush();
        out.close();
        return false;
    }
}
