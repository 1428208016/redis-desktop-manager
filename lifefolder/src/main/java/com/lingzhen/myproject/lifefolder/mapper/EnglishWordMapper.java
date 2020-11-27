package com.lingzhen.myproject.lifefolder.mapper;

import java.util.List;
import java.util.Map;

public interface EnglishWordMapper {

    List lexicon4500List(Map map);

    Map findLexicon4500ById(Long elId);

    int lexicon4500Edit(Map map);

    int exeUpdateSQL(String sql);

    Map findFavoritesById(Map map);

    int insertFavorites(Map map);

    List favoritesList(Map map);

    int deleteFavorites(Map map);


}
