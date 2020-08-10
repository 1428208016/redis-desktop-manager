package com.lingzhen.myproject.entertainment.sort.ddz;

import com.lingzhen.myproject.entertainment.bo.ddz.Poker;

import java.util.Comparator;

public class PokerAsc implements Comparator<Poker> {

	@Override
	public int compare(Poker o1, Poker o2) {
		return o1.getIndex()-o2.getIndex();
	}
}
