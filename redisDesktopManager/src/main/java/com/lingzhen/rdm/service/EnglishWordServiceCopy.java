package com.lingzhen.rdm.service;

import java.util.List;
import java.util.Map;

public interface EnglishWordServiceCopy {
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
    void save(Map pd) throws Exception;

    /**
     * 获取某天的单词，通过用户id，默认当天
     * @param pd
     * @return
     * @throws Exception
     */
    List<Map> getTodayWord(Map pd)throws Exception;

    /**
     * 获取单词-列表
     * @param pd
     * @return
     * @throws Exception
     */
    Map listWord(Map pd) throws Exception;
}
