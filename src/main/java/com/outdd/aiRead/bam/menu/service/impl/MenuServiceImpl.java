package com.outdd.aiRead.bam.menu.service.impl;

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

    @Autowired
    MenuMapper menuMapper;
    @Override
    public List<Menu> findList() {
        System.out.println("asd");
        return menuMapper.findList();
    }
}
