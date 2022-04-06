package com.blog.config;

import com.blog.Interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName MyWebMvcConfigurer
 * @Description TODO
 * @Author admin
 * @Date 2022/4/6 15:53
 * @Version 1.0
 **/
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
//    @Autowired
//    private LoginInterceptor loginInterceptor;
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginInterceptor)
//                .addPathPatterns("/admin/**")
//                .addPathPatterns("/blog/**")
//                .excludePathPatterns("/admin/login")
//                .excludePathPatterns("/admin/register");
//    }
}
