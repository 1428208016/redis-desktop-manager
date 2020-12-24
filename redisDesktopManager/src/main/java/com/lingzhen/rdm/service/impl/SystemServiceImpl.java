package com.lingzhen.rdm.service.impl;

import com.lingzhen.myproject.common.util.DateUtil;
import com.lingzhen.rdm.mapper.SystemMapper;
import com.lingzhen.rdm.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    SystemMapper systemMapper;

    @Override
    public int insertSendSmsLog(Map map) {
        map.put("createDate", DateUtil.getDate());
        map.put("createTime", DateUtil.getTime());
        return systemMapper.insertSendSmsLog(map);
    }

}
