package com.zxx.senior.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: Elite
 * @date: 2018/7/7
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {


//    @Autowired
//    private LoginInterceptor loginInterceptor;

//    @Autowired
//    private SeckInterceptor seckInterceptor;

    /**
     * 全局跨域处理方式
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }

    /**
     * 静态资源映射
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

//    /**
//     * 拦截器配置
//     *
//     * @param registry
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginInterceptor).addPathPatterns("");
//        registry.addInterceptor(seckInterceptor).addPathPatterns("seck/seckill");
//    }

}
