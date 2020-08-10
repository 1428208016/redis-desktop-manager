package com.lingzhen.myproject.oldproject.controller.other.tickets;

import com.lingzhen.myproject.oldproject.controller.base.BaseController;
import com.lingzhen.myproject.oldproject.pojo.Result;
import com.lingzhen.myproject.oldproject.util.PageData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author G50
 *
 */
@Controller
@RequestMapping("/ticket")
public class TicketController extends BaseController {
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("goView")
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("trainTickets/trainTickets");
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
		pd.put("menuId", "0");
		
		return result;
	}
	
	
}
