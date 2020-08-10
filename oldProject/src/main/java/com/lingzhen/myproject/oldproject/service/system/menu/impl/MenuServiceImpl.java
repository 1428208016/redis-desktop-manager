package com.lingzhen.myproject.oldproject.service.system.menu.impl;

import com.lingzhen.myproject.oldproject.pojo.zTreeModel;
import com.lingzhen.myproject.oldproject.util.PageData;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MenuServiceImpl {
	
	/**
	 * 通过父级id查询
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> findByPid(PageData pd) throws Exception;

	/**
	 * list
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> list(PageData pd) throws Exception;
	
	/**
	 * 新增
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public void save(HttpServletRequest request, PageData pd) throws Exception;

	/**
	 * 迭代查询所有数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<zTreeModel> findzTree(PageData pd) throws Exception;

	/**
	 * 迭代查询所有数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> findAll(PageData pd) throws Exception;

	/**
	 * 条件查询
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> findByCondition(PageData pd) throws Exception;

	/**
	 * 删除
	 * @param pd
	 * @throws Exception
	 */
	public void deletes(HttpServletRequest request, PageData pd) throws Exception;
	
	
	

}
