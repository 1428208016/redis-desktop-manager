package com.lingzhen.rdm.util;

import com.lingzhen.myproject.common.util.JWTUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * httpServlet工具类
 * @date 2020-08-23
 * @author lingz
 */
public class HttpServletUtil {

    public static Long getUserId() {
        Long userId = null;
        String token = getToken();
        if (!"".equals(token) && JWTUtil.verifyToken(token)) {
            userId = Long.valueOf(JWTUtil.getValue(token));
        }
        return userId;
    }

    public static String getToken() {
        String token = "";
        HttpServletRequest request = HttpServletUtil.getHttpServletRequest();
        Cookie[] cookies = request.getCookies();
        if (null != cookies && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (JWTUtil.TOKEN.equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        return token;
    }

    /**
     * 得到HttpServletRequest对象
     */
    public static HttpServletRequest getHttpServletRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    /**
     * 获取参数
     * @return
     */
    public static Map getRequestParameter() {
        Map returnMap = new HashMap();

        Map properties = getHttpServletRequest().getParameterMap();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj){
                value = "";
            }else if(valueObj instanceof String[]){
                String[] values = (String[])valueObj;
                for(int i=0;i<values.length;i++){
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length()-1);
            }else{
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }

        return returnMap;
    }

}
