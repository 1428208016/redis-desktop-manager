package com.lingzhen.rdm.service.impl;

import com.lingzhen.myproject.common.util.DateUtil;
import com.lingzhen.rdm.mapper.EnglishWordMapper;
import com.lingzhen.rdm.service.EnglishWordService;
import com.lingzhen.rdm.util.HttpServletUtil;
import com.lingzhen.rdm.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EnglishWordServiceImpl implements EnglishWordService {

    @Autowired
    private EnglishWordMapper englishWordMapper;

    @Override
    public List lexicon4500List(Map map){
        PageUtil.PageHelper(map);
        List data = englishWordMapper.lexicon4500List(map);
        return data;
    }

    @Override
    public Map findLexicon4500ById(Long elId){
        return englishWordMapper.findLexicon4500ById(elId);
    }

    @Override
    public int lexicon4500Edit(Map map) {
        return englishWordMapper.lexicon4500Edit(map);
    }

    @Override
    public int favorites(Long userId, Long elId) {
        Map selMap = new HashMap();
        selMap.put("userId",userId);
        selMap.put("elId",elId);
        Map data = englishWordMapper.findFavoritesById(selMap);
        if (null == data || data.size() <= 0) {
            selMap.put("createDate",DateUtil.getDate());
            selMap.put("createTime",DateUtil.getTime());
            englishWordMapper.insertFavorites(selMap);
        }
        return 1;
    }

    @Override
    public int cancelFavorites(Long userId, Long elId) {
        Map selMap = new HashMap();
        selMap.put("userId",userId);
        selMap.put("elId",elId);
        return englishWordMapper.deleteFavorites(selMap);
    }

    @Override
    public List favoritesList(Map map) {
        PageUtil.PageHelper(map);
        map.put("userId", HttpServletUtil.getUserId());
        List data = englishWordMapper.favoritesList(map);
        return data;
    }

    @Override
    public Map init() {
        Map map = new HashMap();
        try {
            File file = new File("C:\\Users\\Administrator\\Desktop\\englishWord4500.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while(null != (line = bufferedReader.readLine())){
                try {
                    StringBuffer sql = new StringBuffer("INSERT INTO ew_lexicon_4500(word,createDate,createTime) VALUES ");
                    String date = DateUtil.getDate();
                    String time = DateUtil.getTime();
                    for (String word : line.split(" ")) {
                        if (word.isEmpty()) {
                            continue;
                        }
                        sql.append("('").append(word).append("','").append(date).append("','").append(time).append("'),");
                    }
                    englishWordMapper.exeUpdateSQL(sql.toString().substring(0,sql.toString().length()-1));
                } catch (Exception e) {
                    System.out.println("EXCEPTION ERROR:"+e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return map;
    }

}
