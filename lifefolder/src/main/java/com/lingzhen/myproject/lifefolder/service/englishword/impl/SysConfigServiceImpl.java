package com.lingzhen.myproject.lifefolder.service.englishword.impl;

import com.lingzhen.myproject.lifefolder.mapper.englishword.SysConfigMapper;
import com.lingzhen.myproject.lifefolder.service.englishword.SysConfigService;
import com.lingzhen.myproject.lifefolder.util.Tools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 系统配置表实现
 * createTime：2019-06-24
 */
@Service("sysConfig")
public class SysConfigServiceImpl implements SysConfigService {


    private SysConfigMapper sysConfigMapper;

    @Override
    public String findValueByCode(String code) {
        try {
            Map data = sysConfigMapper.findByCode(code);
            if (null != data && Tools.notEmpty(data.get("value").toString())){
                return data.get("value").toString();
            }else{
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }
}
