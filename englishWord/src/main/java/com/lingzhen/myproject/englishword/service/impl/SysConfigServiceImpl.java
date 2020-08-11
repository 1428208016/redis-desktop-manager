package com.lingzhen.myproject.englishword.service.impl;

import com.lingzhen.myproject.englishword.dao.Dao;
import com.lingzhen.myproject.englishword.service.SysConfigService;
import com.lingzhen.myproject.englishword.util.PageData;
import com.lingzhen.myproject.englishword.util.Tools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 系统配置表实现
 * createTime：2019-06-24
 */
@Service("sysConfig")
public class SysConfigServiceImpl implements SysConfigService {

    @Resource(name = "daoImpl")
    private Dao dao;

    @Override
    public String findValueByCode(String code) {
        try {
            PageData data = (PageData) dao.queryOne("sysConfigMapper.findByCode",code);
            if (null != data && Tools.notEmpty(data.getString("value"))){
                return data.getString("value");
            }else{
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }
}
