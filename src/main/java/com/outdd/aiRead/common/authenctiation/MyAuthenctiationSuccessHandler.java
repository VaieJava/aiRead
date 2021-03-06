package com.outdd.aiRead.common.authenctiation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.outdd.aiRead.common.base.BaseApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("myAuthenctiationSuccessHandler")
@Slf4j
public class MyAuthenctiationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
        @Autowired
        private ObjectMapper objectMapper;
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                            Authentication authentication) throws IOException, ServletException {
            log.info("登录成功");
//            response.setContentType("application/json;charset=UTF-8");
//            response.getWriter().write(objectMapper.writeValueAsString(new BaseApiService().setResultSuccess(authentication)));
            String url=request.getRequestURI();
            url="admin/index.do";

            //如果是要跳转到某个页面的
            new DefaultRedirectStrategy().sendRedirect(request, response, url);
        }
    }