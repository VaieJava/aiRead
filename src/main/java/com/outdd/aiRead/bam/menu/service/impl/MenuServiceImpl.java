package com.outdd.aiRead.bam.menu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.outdd.aiRead.bam.menu.dao.MenuMapper;
import com.outdd.aiRead.bam.menu.pojo.Menu;
import com.outdd.aiRead.bam.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author vaie
 * @Created 2018/11/28 17:44
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    MenuMapper menuMapper;
    @Override
    public List<Menu> findList() {
        Menu menu = new Menu();
        menu.setMenuLevel("1");
        menu.setMenuState(1);
        return menuMapper.findList(menu);
    }

    @Override
    public PageInfo<Menu> findList(PageInfo page) {
        PageHelper.startPage(page);
        List<Menu> menus=menuMapper.findList(null);
        return new PageInfo<>(menus);
    }
}
