package com.lingzhen.myproject.entertainment.util.ddz;

import com.lingzhen.myproject.entertainment.bo.ddz.Ddz;
import com.lingzhen.myproject.entertainment.bo.ddz.Player;
import com.lingzhen.myproject.entertainment.bo.ddz.Poker;
import com.lingzhen.myproject.entertainment.sort.ddz.PokerAsc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 操作牌工具类
 * @author G50
 *
 */
public class PokerUtil {
	/**
	 * 初始化 
	 * @return Poker集合
	 */
	public static List<Poker> initPoker(){
		List<Poker> list = new ArrayList<Poker>();
		for(int i = 1;i<=4;i++){
			list.add(new Poker(1,"3",i));
			list.add(new Poker(2,"4",i));
			list.add(new Poker(3,"5",i));
			list.add(new Poker(4,"6",i));
			list.add(new Poker(5,"7",i));
			list.add(new Poker(6,"8",i));
			list.add(new Poker(7,"9",i));
			list.add(new Poker(8,"10",i));
			list.add(new Poker(9,"J",i));
			list.add(new Poker(10,"Q",i));
			list.add(new Poker(11,"K",i));
			list.add(new Poker(12,"A",i));
			list.add(new Poker(13,"2",i));
		}
		list.add(new Poker(14,"小王",1));
		list.add(new Poker(15,"大王",2));
		Collections.shuffle(list);
		return list;
	}
	/**
	 * 发牌
	 * @param players 玩家集合
	 * @param plist   牌集合
	 */
	public static void dealPoker(List<Player> players, List<Poker> plist){
		//发牌
		int i = 0;	//牌的游标
		int pi = 0;	//玩家的游标
		while(true){
			if(pi >= players.size()){
				pi = 0;
			}
			players.get(pi).getPlist().add(plist.get(i));
			pi++;
			i++;
			if(i >= plist.size()-3){
				break;
			}
		}
		//地主牌
		List<Poker> ptwo = new ArrayList<Poker>();
		for(int j = i;j<plist.size();j++){
			ptwo.add(plist.get(j));
		}
		plist.clear();
		plist.addAll(ptwo);
		//玩家清理牌
		//for(Player player : players){
		//	Collections.sort(player.getPlist(),new PokerAsc());
		//}
	}
	/**
	 * 打印牌
	 * @param list 需要打印的牌集合
	 */
	public static void printPoker(List<Poker> list){
		Collections.sort(list,new PokerAsc());
		String row1 = "",row2 = "",row3 = "",row4 = "";
		for (Poker poker : list) {
			row1 += " ___ ";
			row2 += "|   |";
			if(poker.getName().length() >1){
				row3 += "|"+poker.getName()+" |";
			}else{
				row3 += "| "+poker.getName()+" |";
			}
			row4 += "|___|";
		}
		System.out.println(row1);
		System.out.println(row2);
		System.out.println(row3);
		System.out.println(row4);
	}
	/**
	 * 抢地主
	 * @param playList
	 * @param pokerList
	 */
	public static void grabLandlord(List<Player> playList,List<Poker> pokerList){
		System.out.println("-----地主牌：");
		PokerUtil.printPoker(pokerList);
		//do something
		//设置地主,设置地主手牌[后期实现]
		playList.get(0).getPlist().addAll(pokerList);
	}
	
	/**
	 * 检查牌型
	 * @param list
	 */
	public static void checkPoker(List<Poker> odlList,List<Poker> newList){
		
	}
	
	/**
	 * 判断是否存在手牌
	 * @param p 当前出牌人
	 * @param str 出牌字符
	 * @return true:存在手牌，false:缺少手牌
	 */
	public static boolean exisPoker(Player p,String str){
		//初始化标记_
		for(Poker tt : p.getPlist()){
			tt.setStatus_exis(0);
		}
		
		char[] bs = str.toCharArray();
		String str2 = "";
		boolean j = false;
		for(char t : bs){
			if("大".equals(t+"")){
				str2 = "大王";
			}else if("小".equals(t+"")){
				str2 = "小王";
			}else{
				str2 = t+"";
			}
			for(Poker tt : p.getPlist()){
				if(str2.equals(tt.getName()) && tt.getStatus_exis() == 0){
					tt.setStatus_exis(1);
					j = true;
					break;
				}
			}
			if(j){
				j = false;
			}else{
				return false;
			}
		}
		return j;
	}
	public static void play(Ddz d, String str){
		//判断手牌是否存在
		boolean j = true;
		do{
			j = PokerUtil.exisPoker(d.getPlayer(),str);
			if(!j){
				System.out.println("出牌错误！请检查");
			}
		}while(!j);
		//判断牌是否正确
		
		//判断是否大过上家
		
		//替换,
	}
	
	public static void start(){
		System.out.println("-----版本1.0默认地主-----");
		System.out.println("----- 游戏正在初始化  -----");
		Scanner sc = new Scanner(System.in);
		
		Ddz d = new Ddz(2);
		System.out.println("----- 初始化成功！开始游戏  -----");
		System.out.print("请输入游戏名称：");
		
		d.AddPlayList(new Player(1, sc.next(),1));
		d.AddPlayList(new Player(2, "玩家A",0));
		d.AddPlayList(new Player(3, "玩家B",0));
		
		System.out.println("-----正在洗牌");
		d.setNewPokerList(PokerUtil.initPoker());
		System.out.println("-----正在发牌");
		PokerUtil.dealPoker(d.getPlayList(),d.getNewPokerList());
		//抢地主
		PokerUtil.grabLandlord(d.getPlayList(),d.getNewPokerList());
		//出牌环节 
		String sc2 = "";
		while(true){
			d.indexIncrease();
			d.setPlayer(d.getPlayList().get(d.getIndex()%3));
			System.out.println("-----玩家["+d.getPlayer().getName()+"]出牌----------"); 
			PokerUtil.printPoker(d.getPlayer().getPlist());
			System.out.println("-----请出牌(出牌:牌字符,过:1,要不起:0)-----"); 
			sc2 = sc.next();
			//判断是否出牌
			if("0".equals(sc2) || "1".equals(sc2)){
				continue;
			}else{
				PokerUtil.play(d,sc2);
			}
			//打完手牌.跳出循环
			if(d.getPlayer().getPlist().size() <= 0){
				break;
			}
		}
		System.out.println("");
		System.out.println("-----游戏结束-----");
		System.out.println("-----获胜者：【"+d.getPlayer().getName()+"】恭喜！！！-----");
		for (Player playerTemp : d.getPlayList()) {
			if(playerTemp.getPlist().size() > 0){
				System.out.println("-----玩家["+playerTemp.getName()+"]-----");
				PokerUtil.printPoker(playerTemp.getPlist());
			}
		}
	}
	
	public static void main(String[] args) {
		//PokerUtil.printPoker(null);
		PokerUtil.start();
		
		
		
		/*
		
		
		List<Poker> plist = PokerUtil.initPoker();
		PokerUtil.dealPoker(players, plist);
		for (Player player : players) {
			System.out.println(player.getName()+"----------");
			for(Poker p : player.getPlist()){
				System.out.println(p);
			}
			System.out.println("");
		}
		System.out.println("-----end-----");
		for(Poker p : plist){
			System.out.println(p);
		}*/
	}
}
