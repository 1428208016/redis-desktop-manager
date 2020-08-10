package com.lingzhen.myproject.oldproject.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import java.util.Map;


/**
 * 权限处理
 * @author:fh qq 313596790[青苔]
 * 修改日期：2015/11/19
*/
public class Jurisdiction {

	
	/**
	 * 判断用户增删改查按钮权限
	 * @param roleButton
	 * @param MENU_ID
	 * @param isAdmin
	 * @return
	 */
	public static String convertRoleButtonQx(String roleButton, String MENU_ID,Boolean isAdmin) {
		
		String returnStr="0";
		if(isAdmin){
			returnStr="1";
		}else{
			if(roleButton!=null && !"".equals(roleButton)){
				boolean isCheck=false;
				String[] roles = roleButton.split(",");
				for (int i = 0; i < roles.length; i++) {
					if (isCheck) {
						break;
					}
					boolean ishasMenu = false;
					String roleqx = roles[i];
					ishasMenu = RightsHelper.testRights(roleqx, MENU_ID);
					isCheck = ishasMenu;
				}
				if(isCheck){
					returnStr="1";
				}
			}
		}
		return returnStr;
	}

	
	
	/**获取当前登录的用户名
	 * @return
	 */
	public static String getUsername(){
		return getSession().getAttribute(Const.SESSION_USERNAME).toString();
	}
	
	/**获取当前按钮权限(增删改查)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getHC(){
		return (Map<String, String>)getSession().getAttribute(getUsername() + Const.SESSION_QX);
	}
	
	/**shiro管理的session
	 * @return
	 */
	public static Session getSession(){
		//Subject currentUser = SecurityUtils.getSubject();  
		return SecurityUtils.getSubject().getSession();
	}
}
