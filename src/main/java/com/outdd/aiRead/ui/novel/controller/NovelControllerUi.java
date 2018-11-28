package com.outdd.aiRead.ui.novel.controller;

import com.github.pagehelper.PageInfo;
import com.outdd.aiRead.common.util.CommomUtil;
import com.outdd.aiRead.ui.novel.pojo.BookInfo;
import com.outdd.aiRead.ui.novel.pojo.ChapterInfo;
import com.outdd.aiRead.ui.novel.service.CategoryService;
import com.outdd.aiRead.ui.novel.service.NovelServiceUi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * TODO: 小说前端显示
 * @author VAIE
 * @date: 2018/11/9-15:28
 * @version v1.0
 */
@Controller
@RequestMapping("/novelUi/")
public class NovelControllerUi {
    String site = "ui/novel/";
    String airead="_AI讀書網";

    @Autowired
    NovelServiceUi novelServiceUi;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("all")
    public String all(PageInfo<BookInfo> page, ModelMap mo){
        page.setPageSize(20);
        PageInfo<BookInfo> pageEntity = novelServiceUi.getBookInfo(page);
        String asd=CommomUtil.getPageDiv(pageEntity);
        mo.put("page",pageEntity);
        mo.put("pageDiv",asd);
        mo.put("categorys",categoryService.getFirstCategory(null));
        mo.put("title","全部作品"+airead);
        return site+"all";
    }
    /**
     * 返回小说信息页面
     * @return
     */
    @RequestMapping("info")
    public String info (String bookId, ModelMap mo){
        if(CommomUtil.isNotNull(bookId)){
            BookInfo bi=novelServiceUi.getBookInfo(bookId);
            String title="《"+bi.getBookName()+"》"+bi.getAuthor()+airead;
            mo.put("entity",bi);
            mo.put("title",title);
            mo.put("capterNum",bi.getVolumeInfos().get(0).getChapterInfos().size());
        }

        return site+"info";
    }
    /**
     * 返回小说信息页面
     * @return
     */
    @RequestMapping("chapter")
    public String chapter (String volumeId,String chapterId,ModelMap mo){
        String tableName="";
        if(CommomUtil.isNotNull(volumeId)&&CommomUtil.isNotNull(chapterId)){
            Long id=novelServiceUi.getVolumeInfo(volumeId).getBookId();
            String bookId = String.valueOf(id);
            ChapterInfo ci=novelServiceUi.getChapter(volumeId,chapterId,bookId);
            BookInfo bi=null;
            if(CommomUtil.isNotNull(bookId)){
               bi=novelServiceUi.getBookInfo(bookId);
                mo.put("book",bi);
            }
            String title=bi.getBookName()+"_"+ci.getChapterName()+airead+"_小說下載";
            mo.put("entity",ci);
            mo.put("title",title);
        }
        return site+"chapter";
    }
}
