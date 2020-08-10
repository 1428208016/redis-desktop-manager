package com.lingzhen.myproject.oldproject.util;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class HttpUtil {

	/**
	 * get跨域操作
	 * @param pd
	 * @return
	 */
	public static String HttpGet(String url){
		HttpGet method = new HttpGet(url);
		HttpClient httpClient = new DefaultHttpClient();
		String body = "";
		if (method != null ) {
			try {
				//建立一个NameValuePair数组，用于存储欲传送的参数  
				//method.addHeader("Content-type","application/x-www-form-urlencoded; charset=utf-8");
				method.addHeader("Content-type","application/json; charset=utf-8"); 
				method.setHeader("Accept", "application/json");  
				List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>(); 
			//	pairList.add(new BasicNameValuePair("app_id",String.valueOf( new Date() ) ));
			//	method.setEntity((new UrlEncodedFormEntity(pairList,HTTP.UTF_8)));  
				HttpResponse response = httpClient.execute(method);  
				
				int statusCode = response.getStatusLine().getStatusCode();  
				
				if (statusCode != HttpStatus.SC_OK) { 
					//body = EntityUtils.toString(response.getEntity()); 
				}  
				body = EntityUtils.toString(response.getEntity());
			} catch (Exception e) {
				body = "500";
			}
		}
		return body;
	}
	
	/**
	 * post跨域操作
	 * @param url
	 * @return
	 */
	public static void HttpPost(String url,List<PageData> pars){
		HttpClient httpClient=new DefaultHttpClient();
		HttpPost method=new HttpPost(url);
		if(method!=null){
			method.addHeader("Content-type","application/json; charset=utf-8"); 
			method.setHeader("Accept", "application/json");  
			
			List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>(); 
			if(pars.size() > 0){
				for(PageData temp : pars){
					pairList.add(new BasicNameValuePair(temp.getString("key"),temp.getString("val")));  
				}
			}
			try {
				method.setEntity((new UrlEncodedFormEntity(pairList,HTTP.UTF_8)));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				System.out.println("***** setEntity error *****");
			}
			HttpResponse response;
			try {
				response = httpClient.execute(method);
				int statusCode = response.getStatusLine().getStatusCode();  
//			System.out.println(statusCode);
				if (statusCode != HttpStatus.SC_OK) { 
//			throw new Exception("send sms code error");
					//body = EntityUtils.toString(response.getEntity()); 
				}
				System.out.println("***** execute end *****");
				System.out.println(statusCode);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
				System.out.println(e);
				System.out.println("***** execute error *****");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println(e);
				System.out.println("***** execute[IO] error *****");
			}  
		}
	}
}
