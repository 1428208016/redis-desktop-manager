package com.lingzhen.rdm.service.impl;

import com.alibaba.fastjson.JSON;
import com.lingzhen.myproject.common.util.DateUtil;
import com.lingzhen.rdm.mapper.UserMapper;
import com.lingzhen.rdm.service.StoreService;
import com.lingzhen.rdm.service.UserService;
import com.lingzhen.rdm.util.HttpServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StoreService storeService;

    @Override
    public Map findById(Long userId) {
        return userMapper.findById(userId);
    }

    @Override
    public int insertAccessLog(String method, String parameter) {
        Long userId = HttpServletUtil.getUserId();
        int i = 0;
        if (null != userId) {
            String hsra = JSON.toJSONString(HttpServletUtil.getRequestParameter());
            Map map = new HashMap();
            map.put("userId",userId);
            map.put("method",method);
            map.put("parameter",parameter);
            map.put("httpServletRequestAttributes", hsra);
            map.put("remark", "");
            map.put("createTime",DateUtil.getTime());
            map.put("createDate", DateUtil.getDate());

            i = userMapper.insertAccessLog(map);
        }
        return i;
    }

    @Override
    public int initAccount(Long userId) {
        // 初始化项目
        storeService.buyProject(userId,"1");

        return 1;
    }

}