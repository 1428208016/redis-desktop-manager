package com.lingzhen.myproject.jiqun.controller;


import com.lingzhen.myproject.jiqun.util.PageData;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


public class BaseController {



	private static final long serialVersionUID = 6357869213649815390L;

	/**
	 * 得到PageData
	 */
	public PageData getPageData(PageData pd){
		pd.put("userId",this.getRequest().getAttribute("userId"));
		return pd;
	}




	public PageData getPageData(HttpServletRequest request){
		if(request.getAttribute("pd")!=null){
			if(request.getAttribute("pd") instanceof PageData){
				return (PageData)request.getAttribute("pd");
			}else{
				return new PageData(request);
			}
		}else{
			return new PageData(request);
		}

	}

	/**
	 * 得到ModelAndView
	 */
	public ModelAndView getModelAndView(){
		return new ModelAndView();
	}

	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

		return request;
	}

 


	/**
	 * 得到32位的uuid
	 * @return
	 */
	public String get32UUID(){

		//return UUIDTool.getUUID();
		return "1";
	}

	/*
	* 得到PageData
	*/
	public PageData getPageData(){
		return new PageData(this.getRequest());
	}

}
