package com.genue.sseumsseum;

import java.util.ArrayList;

public class UserInfo
{
	private int account;
	private ArrayList<MonthlyIOInfo> monthlyIOInfoList;


	private static UserInfo user;

	private UserInfo(){}

	public static UserInfo getInstance(){
		if(user == null){
			user = new UserInfo();
		}

		return user;
	}


	public int getAccount()
	{
		return account;
	}

	public void setAccount(int account)
	{
		this.account = account;
	}

	public void setMonthlyIOInfo(MonthlyIOInfo monthlyIOInfo){
		this.monthlyIOInfoList.add(monthlyIOInfo);
	}
}
