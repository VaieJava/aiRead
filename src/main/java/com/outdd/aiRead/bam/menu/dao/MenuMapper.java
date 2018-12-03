package com.outdd.aiRead.bam.menu.dao;

import com.outdd.aiRead.bam.menu.pojo.Menu;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuMapper {
    String sql="menu_id, menu_name, menu_url, menu_level, menu_pid, menu_weight, menu_state, menu_icon";
    int deleteByPrimaryKey(String menuId);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(String menuId);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> findList();

    List<Menu> findList(Menu menu);
}