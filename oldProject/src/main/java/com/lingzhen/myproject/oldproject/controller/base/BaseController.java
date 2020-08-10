package com.lingzhen.myproject.oldproject.controller.base;

import com.lingzhen.myproject.oldproject.util.PageData;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mujianjiang 修改时间：2015、12、11
 */
public class BaseController {

	protected Logger logger = Logger.getLogger(this.getClass());

	private static final long serialVersionUID = 6357869213649815390L;

	protected PageData pd = new PageData();
	
	protected ModelAndView mv = this.getModelAndView();
	
	public final static String PROCESS_KEY="PROCESS_KEY";
	
	public final static String PROCESS_TYPE="PROCESS_TYPE";

	/**
	 * new PageData对象
	 * 
	 * @return
	 */
	public PageData getPageData() {
		return new PageData(this.getRequest());
	}
	public PageData getPageData(HttpServletRequest request) {
		return new PageData(request);
	}

	/**
	 * 得到ModelAndView
	 * 
	 * @return
	 */
	public ModelAndView getModelAndView() {
		return new ModelAndView();
	}

	/**
	 * 得到request对象
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		return request;
	}

	/**
	 * 得到32位的uuid
	 * 
	 * @return
	 */
/*	public String get32UUID() {
		return UuidUtil.get32UUID();
	}*/

	/**
	 * 得到分页列表的信息
	 * 
	 * @return
	 */
/*	public Page getPage() {
		return new Page();
	}
*/
	public static void logBefore(Logger logger, String interfaceName) {
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}
	
	/**
	 * 控制台日志
	 * @param logger
	 * @param interfaceName 模块功能描述
	 * @param c 类名
	 * @param parameter 参数
	 */
	public static void logBefore(Logger logger,String interfaceName,Class c, String parameter){
		logger.info("");
		logger.info(">>>>>>>>>>>>>>>>>>>>>>"+interfaceName+"开始>>>>>>>>>>>>>>>>>>>>>>");
		logger.info("日志描述："+interfaceName);
		logger.info("访问类的路径："+c.getResource("").getPath()+c.getName());
		logger.info("日志参数："+parameter);
	}

	public static void logAfter(Logger logger) {
		logger.info("end");
		logger.info("");
	}

	public void writeJson(HttpServletResponse response, Object object)
			throws IOException {

		response.setContentType("text/html;charset=utf-8");
		ObjectMapper objMapper = new ObjectMapper();
		JsonGenerator jsonGenerator = objMapper.getJsonFactory()
				.createJsonGenerator(response.getOutputStream(),
						JsonEncoding.UTF8);
		jsonGenerator.writeObject(object);
		jsonGenerator.flush();
		jsonGenerator.close();

		// String json = JSON.toJSONStringWithDateFormat(object,
		// "yyyy-MM-dd HH:mm:ss");
		// response.setContentType("text/html;charset=utf-8");
		// response.getWriter().write(json);
		// response.getWriter().flush();
		// response.getWriter().close();
	}

}
