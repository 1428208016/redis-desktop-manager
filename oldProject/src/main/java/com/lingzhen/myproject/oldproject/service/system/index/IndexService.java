package com.lingzhen.myproject.oldproject.service.system.index;

import com.lingzhen.myproject.oldproject.dao.DaoSupport;
import com.lingzhen.myproject.oldproject.service.system.index.impl.IndexServiceImpl;
import com.lingzhen.myproject.oldproject.util.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("indexService")
public class IndexService implements IndexServiceImpl {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Override
	public PageData findUserByUserAndPwd(PageData pd) throws Exception {
		return (PageData) dao.findForObject("sysUserMapper.findUserByUserAndPwd", pd);
	}

}
