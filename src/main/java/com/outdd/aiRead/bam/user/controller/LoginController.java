package com.outdd.aiRead.bam.user.controller;

import com.github.pagehelper.PageInfo;
import com.outdd.aiRead.bam.menu.pojo.Menu;
import com.outdd.aiRead.bam.menu.service.MenuService;
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
    String stir="bam/";

    @RequestMapping("/login_page")
    public String list(PageInfo<Menu> page, ModelMap mo) {

        return stir+"login";
    }
}
