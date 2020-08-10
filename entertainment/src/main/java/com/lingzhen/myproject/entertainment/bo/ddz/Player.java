package com.lingzhen.myproject.entertainment.bo.ddz;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private int id;				//玩家id
	private String name;		//名称
	private int type;			//1:地主,0：农民
	private List<Poker> plist;	//玩家手牌
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Poker> getPlist() {
		return plist;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setPlist(List<Poker> plist) {
		this.plist = plist;
	}

	public Player() {}
	/**
	 * 初始化
	 * @param id 玩家id
	 * @param name 名称
	 */
	public Player(int id,String name,int type){
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.plist = new ArrayList<Poker>();
	}
}
