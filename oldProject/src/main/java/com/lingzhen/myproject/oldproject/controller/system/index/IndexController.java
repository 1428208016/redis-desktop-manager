package com.lingzhen.myproject.oldproject.controller.system.index;

import com.lingzhen.myproject.oldproject.controller.base.BaseController;
import com.lingzhen.myproject.oldproject.pojo.User;
import com.lingzhen.myproject.oldproject.system.index.impl.IndexServiceImpl;
import com.lingzhen.myproject.oldproject.service.system.menu.impl.MenuServiceImpl;
import com.lingzhen.myproject.oldproject.util.Const;
import com.lingzhen.myproject.oldproject.util.Jurisdiction;
import com.lingzhen.myproject.oldproject.util.PageData;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author G50
 *
 */
@Controller
@RequestMapping("/main")
public class IndexController extends BaseController {
	
	@Resource(name ="indexService")
	private IndexServiceImpl indexService;

	@Resource(name ="menuService")
	private MenuServiceImpl menuService;
	/**
	 * 跳转至登录页面
	 * @return
	 */
	@RequestMapping("toLogin")
	public ModelAndView toLogin(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/index/login");
		return mv;
	}
	
	/**
	 * 登录验证用户
	 * @throws Exception
	 */
	@RequestMapping(value = "login",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object login(HttpServletRequest request) throws Exception {
		PageData pd = this.getPageData();
		PageData result = new PageData();
		//Session session =  (Session) this.getRequest().getSession();
		//Session session = SecurityUtils.getSubject().getSession();
		HttpSession session = request.getSession();
		Session session2 = Jurisdiction.getSession();
		
		result.put("msg", "登录成功！");
		result.put("result", "1");
		PageData userPD = indexService.findUserByUserAndPwd(pd);
		if(null == userPD){
			result.put("msg", "登录失败！");
			result.put("result", "0");
		}else{
			User user = new User();
			user.setUserId(userPD.getString("userid"));
			user.setUserName(userPD.getString("userName"));
			user.setPassWord(userPD.getString("password"));
			user.setRealName(userPD.getString("realName"));
			//request.setAttribute(Const.SESSION_USER, user);
			session.setAttribute(Const.SESSION_USER, user);
			session2.setAttribute(Const.SESSION_USER, user);
			session2.setAttribute(Const.SESSION_USERNAME, userPD.getString("userName"));
		}
		return result;
	}
	
	/**
	 * 用户退出
	 * @throws Exception
	 */
	@RequestMapping(value = "outLogin",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object outLogin(HttpServletRequest request) throws Exception {
		PageData result = new PageData();
		//Session session =  (Session) this.getRequest().getSession();
		HttpSession session = request.getSession();
		Session session2 = Jurisdiction.getSession();
		String str = (String) session2.getAttribute(Const.SESSION_USERNAME);
		System.out.println("用户名："+str);
		
		result.put("msg", "退出成功！");
		result.put("result", "1");
		session.removeAttribute(Const.SESSION_USER);
		return result;
	}
	
	/**
	 * 跳转至首页
	 * @return
	 */
	@RequestMapping("toIndex")
	public ModelAndView toIndex(){
		ModelAndView mv = new ModelAndView();
		PageData pd = this.getPageData();
		
		List<PageData> list = new ArrayList<PageData>();
		try {
			pd.put("menuId", "1");
			list = menuService.findAll(pd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.addObject("menuList",list);
		mv.setViewName("system/index/index");
		return mv;
	}
	
	/**
	 * 跳转至注册页面
	 * @return
	 */
	@RequestMapping("toRegister")
	public ModelAndView toRegister(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/register");
		return mv;
	}
	
}
