package com.lingzhen.myproject.lifefolder.service;

import com.lingzhen.myproject.lifefolder.pojo.Result;

import java.util.List;
import java.util.Map;

public interface EnglishWordService {

    /**
     * 保存
     * @param map
     * @throws Exception
     */
    Result add(Map map) throws Exception;

    /**
     * 获取单词列表
     * @param map
     * @return
     * @throws Exception
     */
    List list(Map map) throws Exception;
}
