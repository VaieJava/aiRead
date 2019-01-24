package com.outdd.aiRead.common.authenctiation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.outdd.aiRead.common.base.BaseApiService;
import com.outdd.aiRead.common.util.CommomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component("myAuthenctiationFailureHandler")
    public class MyAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

        @Autowired
        private ObjectMapper objectMapper;

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                            AuthenticationException exception) throws IOException, ServletException {

            log.info("登录失败");
            RedirectAttributes attributes;
//            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//            response.setContentType("application/json;charset=UTF-8");
//            response.getWriter().write(objectMapper.writeValueAsString(new BaseApiService().setResultError(exception.getMessage())));
            String url=request.getRequestURI();
            url="/login_page.do";
            Map<String,String> params = new HashMap<>();
            params.put("info",java.net.URLEncoder.encode("失败","UTF-8"));
            CommomUtil.redirect(url,params,response);
        }
    }