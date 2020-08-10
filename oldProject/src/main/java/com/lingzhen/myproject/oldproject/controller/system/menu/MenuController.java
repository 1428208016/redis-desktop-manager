package com.lingzhen.myproject.oldproject.controller.system.menu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lingzhen.myproject.oldproject.controller.base.BaseController;
import com.lingzhen.myproject.oldproject.pojo.Result;
import com.lingzhen.myproject.oldproject.pojo.zTreeModel;
import com.lingzhen.myproject.oldproject.service.system.menu.impl.MenuServiceImpl;
import com.lingzhen.myproject.oldproject.util.PageData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 
 * @author G50
 *
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {
	
	@Resource(name ="menuService")
	private MenuServiceImpl menuService;
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("view")
	public ModelAndView toRegister(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/menu/sys_menu_list");
		return mv;
	}
	
	/**
	 * 加载ztree
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("ztreelist")
	@ResponseBody
	public Object ztreelist() throws Exception{
		Result result = new Result(Result.SUCCESS,"操作成功");
		
		PageData pd = new PageData();
		pd.put("menuId", 0);
		List<zTreeModel> list = menuService.findzTree(pd);
		JSONArray jarr = JSONArray.parseArray(JSON.toJSONString(list));
		result.setData(jarr);
		
		return result;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@ResponseBody
	public Object list() throws Exception{
		Result result = new Result(Result.SUCCESS,"操作成功");
		
		PageData pd = this.getPageData();
		
		try {
			List<PageData> list = menuService.list(pd);
			result.setData(list);
		} catch (Exception e) {
			result = new Result(Result.FAIL,"查询失败！");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 新增
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@ResponseBody
	public Object save(HttpServletRequest request) throws Exception{
		Result result = new Result(Result.SUCCESS,"操作成功");
		
		PageData pd = this.getPageData();
		
		try {
			menuService.save(request,pd);
		} catch (Exception e) {
			e.printStackTrace();
			result = new Result(Result.FAIL,"操作失败");
			result.setOtherMsg(e.getLocalizedMessage());
		}
		
		return result;
	}
	
	/**
	 * 删除
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("dels")
	@ResponseBody
	public Object dels(HttpServletRequest request) throws Exception{
		Result result = new Result(Result.SUCCESS,"操作成功");
		
		PageData pd = this.getPageData();
		
		try {
			menuService.deletes(request,pd);
		} catch (Exception e) {
			e.printStackTrace();
			result = new Result(Result.FAIL,"操作失败");
			result.setOtherMsg(e.getLocalizedMessage());
		}
		
		return result;
	}
	
	
	
	
	
}
