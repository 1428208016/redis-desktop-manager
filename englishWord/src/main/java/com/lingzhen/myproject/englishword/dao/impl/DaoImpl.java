package com.lingzhen.myproject.englishword.dao.impl;

import com.lingzhen.myproject.englishword.dao.Dao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("daoImpl")
public class DaoImpl implements Dao {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public Object save(String str, Object obj) throws Exception {
        return sqlSessionTemplate.insert(str,obj);
    }

    @Override
    public Object update(String str, Object obj) throws Exception {
        return sqlSessionTemplate.update(str,obj);
    }

    @Override
    public Object delete(String str, Object obj) throws Exception {
        return sqlSessionTemplate.delete(str,obj);
    }

    @Override
    public Object queryOne(String str, Object obj) throws Exception {
        return sqlSessionTemplate.selectOne(str,obj);
    }

    @Override
    public Object queryList(String str, Object obj) throws Exception {
        return sqlSessionTemplate.selectList(str,obj);
    }

}
