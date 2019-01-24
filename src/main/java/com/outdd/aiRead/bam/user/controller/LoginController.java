package com.outdd.aiRead.bam.user.controller;

import com.github.pagehelper.PageInfo;
import com.outdd.aiRead.bam.menu.pojo.Menu;
import com.outdd.aiRead.bam.menu.service.MenuService;
import com.outdd.aiRead.bam.user.pojo.User;
import com.outdd.aiRead.bam.user.service.UserApiService;
import com.outdd.aiRead.common.util.CommomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO:登录验证器
 * @author vaie
 * @Created 2018/12/17 17:34
 */
@Controller
public class LoginController {
    @Autowired
    UserApiService userApiService;
    String stir="bam/";

    @RequestMapping("/")
    public String index(String info, ModelMap mo) {


        return "redirect:/admin/index.do";
    }
    @RequestMapping("/login_page")
    public String login_page(String info, ModelMap mo) {

        mo.put("err",info);
        return stir+"login";
    }

    @RequestMapping("/register")
    public String register(String info, ModelMap mo) {

        mo.put("err",info);
        return stir+"pages-register";
    }
    /**
     * 创建一个新用户
     * @param mo
     * @param User
     * @return
     */
    @RequestMapping("registry")
    public String registry(ModelMap mo , User User){
        userApiService.addUser(User);
        return "redirect:/admin/index.do";
    }

}
