package com.outdd.aiRead.common.filterAndListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.outdd.aiRead.common.base.BaseApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO:自定义安全配置的加载机制
 * @author vaie
 * @Created 2018/12/17 10:39
 */
@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity
@Slf4j
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    MyAuthenctiationSuccessHandler myAuthenctiationSuccessHandler;
    @Autowired
    MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;
    // BrowerSecurityConfig
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置user-detail服务
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth)throws Exception{
        //自定义方式
        auth.userDetailsService(myUserDetailsService);
    }

    /**
     * 拦截请求
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http)throws Exception{
        http.authorizeRequests()
                .antMatchers("/","/css/**","/js/**").permitAll()   //任何人都可以访问
                .antMatchers("/admin/**").access("hasRole('ADMIN')")     //持有user权限的用户可以访问
                .antMatchers("/user/**").hasAuthority("ROLE_USER")
                .and()
                .formLogin()    //设置表单登录
                .loginPage("/login_page.do")  //设置登录页面
                .loginProcessingUrl("/login")  // 自定义的登录接口
                .successHandler(myAuthenctiationSuccessHandler) // 自定义登录成功处理
                .failureHandler(myAuthenctiationFailureHandler); // 自定义登录失败处理;

    }

    /**
     * 拦截请求
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {

    }

    @Component("myAuthenctiationSuccessHandler")
    class MyAuthenctiationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
        @Autowired
        private ObjectMapper objectMapper;
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                            Authentication authentication) throws IOException, ServletException {
            log.info("登录成功");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new BaseApiService().setResultSuccess(authentication)));
        }
    }
    @Component("myAuthenctiationFailureHandler")
    public class MyAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

        @Autowired
        private ObjectMapper objectMapper;

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                            AuthenticationException exception) throws IOException, ServletException {

            log.info("登录失败");

            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new BaseApiService().setResultError(exception.getMessage())));
        }
    }
}

