package com.outdd.aiRead.common.filterAndListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * TODO:自定义安全配置的加载机制
 * @author vaie
 * @Created 2018/12/17 10:39
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Autowired
    MyUserDetailsService myUserDetailsService;

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
                .and().formLogin()
                .loginPage("/login_page.do").usernameParameter("username").passwordParameter("password")
                .and().exceptionHandling().accessDeniedPage("/loginfail");
    }

    /**
     * 拦截请求
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {

    }
}

