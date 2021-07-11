package com.genue.sseumsseum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class InsertMonthlySpendFragment extends Fragment
{
	private static InsertMonthlySpendFragment instance = null;

	public static InsertMonthlySpendFragment getInstance() {
		if(instance == null){
			instance = new InsertMonthlySpendFragment();
		}
		return instance;
	}

	private InsertMonthlySpendFragment() {}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_insert_monthly_spend, container, false);
	}
}
