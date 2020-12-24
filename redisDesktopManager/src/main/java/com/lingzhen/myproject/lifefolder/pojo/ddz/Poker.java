package com.lingzhen.myproject.lifefolder.pojo.ddz;

/**
 * 牌对象
 * @author G50
 *
 */
public class Poker {

	private int index;		//排序
	private String name;	//名称
	private int type;		//类型 黑 红 梅 方

	private int status_exis = 0;//程序判断（是否已经标记）

	public int getStatus_exis() {
		return status_exis;
	}
	public void setStatus_exis(int status_exis) {
		this.status_exis = status_exis;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	public Poker() {}
	/**
	 * 初始化牌
	 * @param index 排序字段
	 * @param name 牌名称
	 * @param type 牌类型
	 */
	public Poker(int index,String name,int type){
		super();
		this.index = index;
		this.name = name;
		this.type = type;
	}

	@Override
	public String toString() {
		return "{index:"+this.index+",name:"+this.name+",type:"+this.type+"}";
	}
}
