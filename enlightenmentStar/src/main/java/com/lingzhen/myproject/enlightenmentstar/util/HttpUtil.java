package com.lingzhen.myproject.enlightenmentstar.util;

import java.net.URL;
import java.net.URLConnection;

public class HttpUtil {

	public static String request(String str){
		String msg = "";
		try {
			URL url = new URL(str);
			URLConnection urlConn = url.openConnection();
			urlConn.connect();
			
			msg = urlConn.getContent().toString();
			return msg;
		} catch (Exception e) {
			return msg;
		}
	}
}
