package com.lingzhen.myproject.englishword.dao;

public interface Dao {
    /**
     * 新增
     * @return
     * @throws Exception
     */
    Object save(String str, Object obj) throws Exception;
    /**
     * 更新
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    Object update(String str, Object obj) throws Exception;
    /**
     * 删除
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    Object delete(String str, Object obj) throws Exception;
    /**
     * 查询 返回对象
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    Object queryOne(String str, Object obj) throws Exception;
    /**
     * 查询 返回list
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    Object queryList(String str, Object obj) throws Exception;
}
