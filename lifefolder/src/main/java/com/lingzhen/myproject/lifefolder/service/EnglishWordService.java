package com.lingzhen.myproject.lifefolder.service;

import java.util.List;
import java.util.Map;

public interface EnglishWordService {

    /**
     * 词库4500列表
     * @return
     * @throws Exception
     */
    List lexicon4500List(Map map) throws Exception;

    Map init();
}
