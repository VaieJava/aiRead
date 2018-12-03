package com.outdd.aiRead.bam.controller;

import com.outdd.aiRead.bam.menu.pojo.Menu;
import com.outdd.aiRead.bam.menu.service.MenuService;
import com.outdd.aiRead.bam.menu.service.impl.MenuServiceImpl;
import com.outdd.aiRead.common.util.CommomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * TODO: 后台控制器
 * @author VAIE
 * @date: 2018/11/17-15:14
 * @version v1.0
 */
@Controller
@RequestMapping("/admin/")
public class BamController {
    @Autowired
    MenuService menuService;

    @RequestMapping("index")
    public String admin(ModelMap mo) {
        List<Menu> menuList = menuService.findList();
        mo.put("menu", menuFormat(menuList));
        mo.put("title", "AI阅读——后台管理系统");
        return "bam/bam_admin";
    }



    public String menuFormat(List<Menu> menuList) {
        StringBuilder result = new StringBuilder();
            result.append(forMenu(menuList));
        result.insert(0, "<ul id=\"mainnav-menu\" class=\"list-group\">").append("</ul>\n");
        flag= true;
        return result.toString();
    }
    boolean flag= true;
    public String forMenu(List<Menu> menuList){
        String result="";

        if (CommomUtil.isNotNull(menuList) && menuList.size() != 0){
            for (Menu menu : menuList) {
                String css="";
                String content="";

                if(flag){
                    if( "0".equals(menu.getMenuLevel())){
                        css = "list-header";
                        content+="<li class=\""+css+"\">";
                        content+=menu.getMenuName();
                        content+="</li>";
                    }
                    if( "1".equals(menu.getMenuLevel())){
                        content+="<li class=\""+css+"\">";
                        content+="<a href=\"#\">";
                        content+="<i class=\""+menu.getMenuIcon()+"\"></i>";
                        content+="<span class=\"menu-title\">"+menu.getMenuName()+"</span>";
                        content+="<i class=\"arrow\"></i>";
                        content+="</a>";
                        content+="<ul class=\"collapse\">";
                        flag= false;
                    }
                }else{
                    if( "0".equals(menu.getMenuLevel())){
                        content+="</ul>";
                        content+="</li>";
                        css = "list-header";
                        content+="<li class=\""+css+"\">";
                        content+=menu.getMenuName();
                        content+="</li>";
                        flag= true;

                    }
                    if( "1".equals(menu.getMenuLevel())){
                        content+="</ul>";
                        content+="</li>";
                        content+="<li class=\""+css+"\">";
                        content+="<a href=\"#\">";
                        content+="<i class=\""+menu.getMenuIcon()+"\"></i>";
                        content+="<span class=\"menu-title\">"+menu.getMenuName()+"</span>";
                        content+="<i class=\"arrow\"></i>";
                        content+="</a>";
                        content+="<ul class=\"collapse\">";
                        flag= false;
                    }
                }



                if( "2".equals(menu.getMenuLevel())){
                    content+="<li><a href=\"#\" targett=\""+menu.getMenuUrl()+"\">"+menu.getMenuName()+"</a></li>";
                }
                result+=content;
                if(CommomUtil.isNotNull(menu.getMenus()) && menu.getMenus().size() != 0){
                    result+=forMenu(menu.getMenus());
                }
            }
        }
        return result;
    }
}
