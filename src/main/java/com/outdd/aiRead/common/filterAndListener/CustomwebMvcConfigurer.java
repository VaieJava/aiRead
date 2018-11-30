package com.outdd.aiRead.common.filterAndListener;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/*
 * TODO: spring boot 配置mvc 后缀访问
 * @author VAIE
 * @date: 2018/11/11-17:58
 * @version v1.0
 */

@Configuration
public class CustomwebMvcConfigurer  implements WebMvcConfigurer {
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseRegisteredSuffixPatternMatch(true);
    }
    @Bean
    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean registration = new ServletRegistrationBean(
                dispatcherServlet);
        registration.addUrlMappings("*.do");
        registration.addUrlMappings("*.html");
        registration.addUrlMappings("*.css");
        registration.addUrlMappings("*.js");
        registration.addUrlMappings("*.png");
        registration.addUrlMappings("*.gif");
        registration.addUrlMappings("*.ico");
        registration.addUrlMappings("*.jpeg");
        registration.addUrlMappings("*.jpg");
        registration.addUrlMappings("*.eot");
        registration.addUrlMappings("*.ttf");
        registration.addUrlMappings("*.woff");
        registration.addUrlMappings("*.svg");
        return registration;
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 配置模板资源路径
        registry.addResourceHandler("/templates/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/templates/");
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//
        registry.addInterceptor(new LocaleChangeInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**","/login/main");
    }


}
