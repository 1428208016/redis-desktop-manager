package com.lingzhen.myproject.lifefolder.service.impl;

import com.lingzhen.myproject.lifefolder.mapper.StoreMapper;
import com.lingzhen.myproject.lifefolder.pojo.Result;
import com.lingzhen.myproject.lifefolder.service.StoreService;
import com.lingzhen.myproject.lifefolder.util.HttpServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreMapper storeMapper;

    @Override
    public List myProject() {
        Long userId = HttpServletUtil.getUserId();
        return storeMapper.myProject(userId);
    }

    @Override
    public List projectList(Map map) {
        return null;
    }

    @Override
    public Result buyProject(Map map) {
        return null;
    }
}
