package com.blog.config;

import com.blog.Interceptor.LoginInterceptor;
import com.blog.constants.UploadConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/admin/**")
                .addPathPatterns("/blog/**")
                .excludePathPatterns("/admin/user/login") // 登录接口
                .excludePathPatterns("/admin/user/logout") // 登出接口
                .excludePathPatterns("/swagger-ui/index.html"); // Swagger接口文档
    }
    /**
     * @Description: 重写addResourceHandlers映射文件路径
     * @Param: [registry]
     * @return: void
     * @date: 2019/8/7 9:06
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/authorImg/**").addResourceLocations("file:" + UploadConstants.UPLOAD_AUTHOR_IMG);
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + UploadConstants.FILE_UPLOAD_DIC);
    }
}
