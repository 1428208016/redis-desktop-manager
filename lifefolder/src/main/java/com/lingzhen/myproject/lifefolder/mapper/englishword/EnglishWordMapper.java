package com.lingzhen.myproject.lifefolder.mapper.englishword;

import java.util.List;
import java.util.Map;

public interface EnglishWordMapper {

    Map findByWord(String word);

    int save(Map map);

    int saveHtml(Map map);

    int updateByWord(Map map);

    int updateHtmlByWord(Map map);

    int findUserTabMaxRow(Map map);

    Map findRandomWord(Map map);

    List listWordlistPage(Map map);
}
