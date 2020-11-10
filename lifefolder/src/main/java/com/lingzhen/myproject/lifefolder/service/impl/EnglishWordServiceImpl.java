package com.lingzhen.myproject.lifefolder.service.impl;

import com.lingzhen.myproject.common.util.DateUtil;
import com.lingzhen.myproject.lifefolder.mapper.EnglishWordMapper;
import com.lingzhen.myproject.lifefolder.service.EnglishWordService;
import com.lingzhen.myproject.lifefolder.util.PageUtil;
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
    public List lexicon4500List(Map map) throws Exception {
        PageUtil.PageHelper(map);
        List data = englishWordMapper.lexicon4500List(map);
        return data;
    }

    @Override
    public Map init() {
        Map map = new HashMap();
        try {
            File file = new File("C:\\Users\\Administrator\\Desktop\\ew.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while(null != (line = bufferedReader.readLine())){
                StringBuffer sql = new StringBuffer("INSERT INTO ew_lexicon_4500(word,createDate,createTime) VALUES ");
                String date = DateUtil.getDate();
                String time = DateUtil.getTime();
                for (String word : line.split(" ")) {
                    sql.append("('").append(word).append("','").append(date).append("','").append(time).append("'),");
                }
                englishWordMapper.exeUpdateSQL(sql.toString().substring(0,sql.toString().length()-1));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return map;
    }

}
