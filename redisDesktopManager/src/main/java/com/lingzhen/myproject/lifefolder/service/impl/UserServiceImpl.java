package com.lingzhen.myproject.lifefolder.service.impl;

import com.alibaba.fastjson.JSON;
import com.lingzhen.myproject.common.util.DateUtil;
import com.lingzhen.myproject.common.util.JWTUtil;
import com.lingzhen.myproject.common.util.PasswordStorage;
import com.lingzhen.myproject.lifefolder.mapper.UserMapper;
import com.lingzhen.myproject.lifefolder.pojo.Result;
import com.lingzhen.myproject.lifefolder.pojo.ResultInfo;
import com.lingzhen.myproject.lifefolder.service.StoreService;
import com.lingzhen.myproject.lifefolder.service.UserService;
import com.lingzhen.myproject.lifefolder.util.HttpServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
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
