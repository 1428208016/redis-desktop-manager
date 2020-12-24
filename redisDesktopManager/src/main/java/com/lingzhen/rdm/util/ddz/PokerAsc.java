package com.lingzhen.rdm.util.ddz;

import com.lingzhen.rdm.pojo.ddz.Poker;

import java.util.Comparator;

public class PokerAsc implements Comparator<Poker> {

	@Override
	public int compare(Poker o1, Poker o2) {
		return o1.getIndex()-o2.getIndex();
	}
}
