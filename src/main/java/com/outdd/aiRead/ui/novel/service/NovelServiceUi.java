package com.outdd.aiRead.ui.novel.service;

import com.github.pagehelper.PageInfo;
import com.outdd.aiRead.ui.novel.pojo.BookInfo;
import com.outdd.aiRead.ui.novel.pojo.ChapterInfo;
import com.outdd.aiRead.ui.novel.pojo.VolumeInfo;

import java.util.List;
/*
 * TODO: 小说接口定义
 * @author VAIE
 * @date: 2018/11/9-15:52
 * @version v1.0
 */
public interface NovelServiceUi {
    /**
     * TODO:id查询获取小说信息
     * @param id
     * @return
     */
    public BookInfo getBookInfo(String id);

    /**
     * TODO:条件查询获取小说信息
     * @param book
     * @return
     */
    public List<BookInfo> getBookInfo(BookInfo book);

    /**
     *TODO:获取卷信息
     * @param bookId
     * @return
     */
    public VolumeInfo getVolumeInfo(String bookId);

    /**
     *TODO:获取章节信息
     * @param volumeId
     * @param chapterId
     * @return
     */
    public ChapterInfo getChapter(String volumeId, String chapterId,String bookId);

    public PageInfo<BookInfo> getBookInfo(PageInfo page);
}
