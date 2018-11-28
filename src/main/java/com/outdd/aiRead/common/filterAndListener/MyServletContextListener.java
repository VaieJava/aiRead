package com.outdd.aiRead.common.filterAndListener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Date;

/**
 * TODO:使用@WebListener注解，实现ServletContextListener接口
 * @author vaie
 * @create 2018-11-11
 */
@WebListener
@Slf4j
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info(new Date()+"ServletContex初始化");
        log.info(sce.getServletContext().getServerInfo());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("ServletContex销毁");
    }

}
