package com.lingzhen.myproject.entertainment.bo.ddz;

import java.util.ArrayList;
import java.util.List;


public class Ddz {

	private int index;					//当前游标
	private List<Poker> oldPokerList;	//废牌池
	private List<Poker> newPokerList;	//准备发的牌
	private List<Player> playList;		//玩家集合
	private Player player;				//当前出牌对象
	private List<Poker> playPokerList;	//上家出的牌

	/**
	 * 自增index
	 */
	public void indexIncrease(){
		this.index++;
	}
	/**
	 * 添加已打出的牌
	 * @param list
	 */
	public void oldPokerListAdd(List<Poker> list){
		this.oldPokerList.addAll(list);
	}
	/**
	 * 添加玩家
	 * @param p
	 */
	public void AddPlayList(Player p){
		this.playList.add(p);
	}
	/**
	 * 获取玩家
	 * @param i
	 * @return
	 */
	public Player getplays(int i){
		return this.playList.get(i);
	}

	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public List<Poker> getOldPokerList() {
		return oldPokerList;
	}
	public void setOldPokerList(List<Poker> oldPokerList) {
		this.oldPokerList = oldPokerList;
	}
	public List<Poker> getNewPokerList() {
		return newPokerList;
	}
	public void setNewPokerList(List<Poker> newPokerList) {
		this.newPokerList = newPokerList;
	}
	public List<Player> getPlayList() {
		return playList;
	}
	public void setPlayList(List<Player> playList) {
		this.playList = playList;
	}

	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public List<Poker> getPlayPokerList() {
		return playPokerList;
	}
	public void setPlayPokerList(List<Poker> playPokerList) {
		this.playPokerList = playPokerList;
	}

	/**
	 * 初始化游戏局对象.
	 * @param index 游标
	 */
	public Ddz(int index){
		this.index = index;
		this.oldPokerList = new ArrayList<Poker>();
		this.newPokerList = new ArrayList<Poker>();
		this.playList = new ArrayList<Player>();
		this.playPokerList = new ArrayList<Poker>();
		this.player = new Player();
	}


}
