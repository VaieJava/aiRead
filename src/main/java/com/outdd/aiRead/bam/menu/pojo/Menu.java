package com.outdd.aiRead.bam.menu.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Menu implements Serializable {
    private String menuId;

    private String menuName;

    private String menuUrl;

    private String menuLevel;

    private String menuPid;

    private Integer menuWeight;

    private Integer menuState;

    private String menuIcon;

    private List<Menu> menus;

    private static final long serialVersionUID = 1L;

}