package com.outdd.aiRead.bam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * TODO: 后台控制器
 * @author VAIE
 * @date: 2018/11/17-15:14
 * @version v1.0
 */
@Controller
@RequestMapping("/admin/")
public class BamController {
    @RequestMapping("index")
    private String admin(ModelMap mo){
        mo.put("title","AI阅读——后台管理系统");
        return "bam/bam_admin";
    }
}
