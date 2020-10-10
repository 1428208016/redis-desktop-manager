package com.lingzhen.myproject.lifefolder.service.impl;

import com.lingzhen.myproject.lifefolder.mapper.StoreMapper;
import com.lingzhen.myproject.lifefolder.pojo.Result;
import com.lingzhen.myproject.lifefolder.service.StoreService;
import com.lingzhen.myproject.lifefolder.util.HttpServletUtil;
import com.lingzhen.myproject.lifefolder.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        PageUtil.PageHelper(map);
        map.put("userId",HttpServletUtil.getUserId());
        return storeMapper.projectList(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result buyProject(String projectId) {
        Result result = new Result();

        Map data = storeMapper.findById(projectId);
        if (null == data || data.size() <= 0) {
            result.setError("项目不存在！");
            return result;
        }
        if (!"1".equals(data.get("state").toString())) {
            result.setError("项目非正常状态");
            return result;
        }

//        data = storeMapper.findMyProject(selMap);

//        storeMapper.insertProject();

        return result;
    }
}
