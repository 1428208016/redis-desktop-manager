package com.lingzhen.myproject.lifefolder.service.impl;

import com.lingzhen.myproject.lifefolder.mapper.EnglishWordMapper;
import com.lingzhen.myproject.lifefolder.service.EnglishWordService;
import com.lingzhen.myproject.lifefolder.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
