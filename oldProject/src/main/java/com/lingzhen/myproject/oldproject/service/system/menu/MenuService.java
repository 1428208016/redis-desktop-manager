package com.lingzhen.myproject.oldproject.system.menu;

import com.lingzhen.myproject.oldproject.dao.DaoSupport;
import com.lingzhen.myproject.oldproject.pojo.User;
import com.lingzhen.myproject.oldproject.pojo.zTreeModel;
import com.lingzhen.myproject.oldproject.service.system.menu.impl.MenuServiceImpl;
import com.lingzhen.myproject.oldproject.util.Const;
import com.lingzhen.myproject.oldproject.util.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("menuService")
public class MenuService implements MenuServiceImpl {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> findByPid(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("sysMenuMapper.findByPid", pd);
	}

	@Override
	public List<zTreeModel> findzTree(PageData pd) throws Exception {
		List<zTreeModel> list = new ArrayList<zTreeModel>();
		
		List<PageData> data = (List<PageData>) dao.findForList("sysMenuMapper.findByPid", pd);
		for(PageData temp : data){
			zTreeModel z = new zTreeModel();
			z.setId((int)temp.get("menuId"));
			z.setName(temp.getString("menuName"));
			z.setpId((int)pd.get("menuId"));
			List<zTreeModel> sonlist = this.findzTree(temp);
			if(sonlist.size()>0){
				z.setChildren(sonlist);	
			}
			list.add(z);
		}
		return list;
	}

	@Override
	public List<PageData> findByCondition(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("sysMenuMapper.findByCondition", pd);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> list(PageData pd) throws Exception {
		pd.put("search",pd.getString("search[value]").trim());
		return (List<PageData>) dao.findForList("sysMenuMapper.list", pd);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> findAll(PageData pd) throws Exception {
		
		List<PageData> list = (List<PageData>) dao.findForList("sysMenuMapper.findByPidAndIsDisable", pd);
		for(PageData temp : list){
			List<PageData> tlist = this.findAll(temp);
			if(tlist.size() > 0 ){
				temp.put("sonList", tlist);
			}
		}
		return list;
	}

	@Override
	public void save(HttpServletRequest request,PageData pd) throws Exception {
		//Session session = SecurityUtils.getSubject().getSession();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		
		pd.put("isDisable", 1);
		pd.put("createId", user.getUserId());
		pd.put("createTime","");
		pd.put("modifyId", user.getUserId());
		pd.put("modifyTime","");
		pd.put("deleteflag", 1);
		
		dao.save("sysMenuMapper.save", pd);
	}

	@Override
	public void deletes(HttpServletRequest request,PageData pd) throws Exception {
		
	//	pd.put("ids", str);
		List<String> str = Arrays.asList(pd.getString("ids").split(","));
		pd.put("ids", str);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		pd.put("modifyTime", "");
		pd.put("modifyId", user.getUserId());
		dao.delete("sysMenuMapper.deletes", pd);
	}
	
	
}
