package com.outdd.aiRead.common.filterAndListener;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/*
 * TODO: 全局异常捕获
 * @author VAIE
 * @date: 2018/10/19-22:03
 * @version v1.0
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public String defaultExceptionHandler(HttpServletRequest req, Exception  e , ModelMap mo) {

        e.printStackTrace();
        String resl="对不起服务器繁忙<br/>";
        resl+=e.toString()+"<br/>";
        resl+=e.getMessage()+"<br/>";
        resl+=e.getLocalizedMessage()+"<br/>";
        resl+=e+"<br/>";

        mo.put("resl",resl);
        // 返回String
        return "common/500";

        // 返回View 创建ModelAndView  mv = new ModelAndView("error")
    }

}
