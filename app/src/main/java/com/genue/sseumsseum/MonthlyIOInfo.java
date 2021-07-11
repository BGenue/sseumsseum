package com.genue.sseumsseum;

public class MonthlyIOInfo
{
	int title;
	int money;
	int type;//월급, 저축, 지출
	int dayType;//매일, 매주, 매월, 매년
	String explain;

	public MonthlyIOInfo(int title, int money, int type, int dayType){
		this.title = title;
		this.money = money;
		this.type = type;
		this.dayType = dayType;
		this.explain = "";
	}

	public MonthlyIOInfo(int title, int money, int type, int dayType, String explain){
		this.title = title;
		this.money = money;
		this.type = type;
		this.dayType = dayType;
		this.explain = explain;
	}
}
