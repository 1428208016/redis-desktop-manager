package com.lingzhen.myproject.englishword.service;

import com.lingzhen.myproject.englishword.util.PageData;

import java.util.List;

public interface EnglishwordService {
    /**
     * 读取文件初始化ewWord表
     * @throws Exception
     */
    void init() throws Exception;

    /**
     * 读取文件初始化ewWordHtml表
     * @throws Exception
     */
    void init_html() throws Exception;

    /**
     * 保存
     * @param pd
     * @throws Exception
     */
    void save(PageData pd) throws Exception;

    /**
     * 获取某天的单词，通过用户id，默认当天
     * @param pd
     * @return
     * @throws Exception
     */
    List<PageData> getTodayWord(PageData pd)throws Exception;

    /**
     * 获取单词-列表
     * @param pd
     * @return
     * @throws Exception
     */
    PageData listWord(PageData pd) throws Exception;
}
