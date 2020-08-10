package com.lingzhen.myproject.oldproject.service.system.index.impl;

import com.lingzhen.myproject.oldproject.util.PageData;

public interface IndexServiceImpl {
	
	/**
	 * 通过用户名密码查询
	 * @return
	 */
	public PageData findUserByUserAndPwd(PageData pd) throws Exception;

}
