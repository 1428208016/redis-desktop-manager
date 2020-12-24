package com.lingzhen.rdm.service.impl;

import com.lingzhen.myproject.common.util.DateUtil;
import com.lingzhen.rdm.mapper.StoreMapper;
import com.lingzhen.rdm.pojo.Result;
import com.lingzhen.rdm.service.StoreService;
import com.lingzhen.rdm.util.HttpServletUtil;
import com.lingzhen.rdm.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreMapper storeMapper;

    @Override
    public List myProjectAll() {
        Long userId = HttpServletUtil.getUserId();
        return storeMapper.myProjectAll(userId);
    }

    @Override
    public List projectList(Map map) {
        PageUtil.PageHelper(map);
        map.put("userId",HttpServletUtil.getUserId());
        return storeMapper.projectList(map);
    }

    @Override
    public Result buyProject(String projectId) {
        Long userId = HttpServletUtil.getUserId();
        Result result = this.buyProject(userId,projectId);
        return result;
    }

    @Override
    public Result buyProject(Long userId, String projectId) {
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

        Map selMap = new HashMap();
        selMap.put("userId",userId);
        selMap.put("projectId",projectId);
        data = storeMapper.findMyProject(selMap);
        if (null != data && data.size() > 0) {
            result.setError("项目已存在");
            return result;
        }

        selMap.put("createTime", DateUtil.getTime());
        selMap.put("createDate",DateUtil.getDate());
        storeMapper.addUserProject(selMap);

        return result;
    }
}
