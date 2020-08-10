package com.lingzhen.myproject.oldproject.pojo;

public class Result {
	
	public static final String SUCCESS = "1";
	public static final String FAIL = "0";
	public static final String ERROR = "2";

	private String result;
	
	private String msg;
	
	private Object data;
	
	private String otherMsg;

	public Result(){
	}
	public Result(String result,String msg){
		this.result = result;
		this.msg = msg;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getOtherMsg() {
		return otherMsg;
	}

	public void setOtherMsg(String otherMsg) {
		this.otherMsg = otherMsg;
	}
	
	
}
