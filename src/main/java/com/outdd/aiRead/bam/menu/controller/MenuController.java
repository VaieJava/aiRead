package com.outdd.aiRead.bam.menu.controller;

import com.github.pagehelper.PageInfo;
import com.outdd.aiRead.bam.menu.pojo.Menu;
import com.outdd.aiRead.bam.menu.service.MenuService;
import com.outdd.aiRead.common.util.CommomUtil;
import com.outdd.aiRead.ui.novel.pojo.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/*
 * TODO: 菜单控制器
 * @author VAIE
 * @date: 2018/12/1-15:14
 * @version v1.0
 */
@Controller
@RequestMapping("/menu/")
public class MenuController {
    String stir="bam/menu/";
    @Autowired
    MenuService menuService;
    @RequestMapping("list")
    public String list(PageInfo<Menu> page, ModelMap mo) {
        page.setPageSize(10);
        PageInfo<Menu> pageEntity = menuService.findList(page);
        String asd=CommomUtil.getPageDivAdmin(pageEntity);
        mo.put("page",pageEntity);
        mo.put("asd",asd);
        mo.put("title", "AI阅读——菜单管理");
        return stir+"list";
    }


    @RequestMapping("queryById")
    public String queryById(String id,ModelMap mo){
        return stir+"update";
    }

    @RequestMapping("update")
    public String update(ModelMap mo){
        mo.put("title", "AI阅读——修改");
        return stir+"update";
    }

    @RequestMapping("create")
    public String create(ModelMap mo){
        mo.put("title", "AI阅读——新增");
        return stir+"create";
    }
}
