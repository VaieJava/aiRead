package com.outdd.aiRead.ui.novel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.outdd.aiRead.ui.novel.dao.BookInfoMapper;
import com.outdd.aiRead.ui.novel.dao.ChapterInfoMapper;
import com.outdd.aiRead.ui.novel.dao.VolumeInfoMapper;
import com.outdd.aiRead.ui.novel.pojo.BookInfo;
import com.outdd.aiRead.ui.novel.pojo.ChapterInfo;
import com.outdd.aiRead.ui.novel.pojo.VolumeInfo;
import com.outdd.aiRead.ui.novel.service.NovelServiceUi;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/* * TODO: 接口实现
 * @author VAIE
 * @date: 2018/11/9-15:52
 * @version v1.0
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class NovelServiceUiImpl implements NovelServiceUi {
    @Resource
    BookInfoMapper bookInfoMapper;
    @Resource
    VolumeInfoMapper volumeInfoMapper;
    @Resource
    ChapterInfoMapper chapterInfoMapper;
    @Override
    public BookInfo getBookInfo(String id) {

        return bookInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<BookInfo> getBookInfo(BookInfo book) {

        return bookInfoMapper.selectByEntity(book);
    }

    @Override
    public PageInfo<BookInfo> getBookInfo(PageInfo page) {
        PageHelper.startPage(page);
        List<BookInfo> bookInfoList =bookInfoMapper.selectByEntity();
        return new PageInfo<>(bookInfoList);
    }


    @Override
    public VolumeInfo getVolumeInfo(String volumeId) {


        return volumeInfoMapper.selectByPrimaryKey(volumeId);
    }

    public ChapterInfo getChapter(String volumeId, String chapterId,String bookId){
        ChapterInfo ci= new ChapterInfo();
        ci.setVolumeId(Long.valueOf(volumeId));
        ci.setChapterId(Long.valueOf(chapterId));
        ci.setTableName(bookId);
        try {
            ci=chapterInfoMapper.selectByEntity(ci).get(0);
        }catch (Exception e){

        }

        return ci;
    }
}
