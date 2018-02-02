package com.framework.meteor.framework.config;

import com.framework.meteor.framework.interceptor.AuthInterceptor;
import com.framework.meteor.framework.interceptor.UserRoleInteceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Meteor.wu
 * @since 2017/12/5 15:53
 */
@Configuration
public class WebMvcAppConfigurer extends WebMvcConfigurerAdapter {

    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/user/pub/register")
                .excludePathPatterns("/user/pub/forgetPwd")
                .excludePathPatterns("/user/pub/sendVerCode")
                .excludePathPatterns("/user/pub/login")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/v2/**");
        registry.addInterceptor(new UserRoleInteceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
