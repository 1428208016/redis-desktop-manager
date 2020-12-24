package com.lingzhen.rdm.service;

import java.util.List;
import java.util.Map;

public interface EnglishWordService {

    /**
     * 词库4500列表
     * @return
     * @throws Exception
     */
    List lexicon4500List(Map map);

    /**
     * 4500词库findById
     * @param elId
     * @return
     * @throws Exception
     */
    Map findLexicon4500ById(Long elId);

    /**
     * 修改
     * @param map
     * @return
     */
    int lexicon4500Edit(Map map);

    /**
     * 加入收藏夹
     * @param userId
     * @param elId
     * @return
     */
    int favorites(Long userId, Long elId);

    /**
     * 取消收藏
     * @param userId
     * @param elId
     * @return
     */
    int cancelFavorites(Long userId, Long elId);

    /**
     * 收藏夹列表
     * @param map
     * @return
     * @throws Exception
     */
    List favoritesList(Map map);

    Map init();
}
