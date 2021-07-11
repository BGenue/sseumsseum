package com.genue.sseumsseum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class InsertMonthlySaveFragment extends Fragment
{
	private static InsertMonthlySaveFragment instance = null;

	public static InsertMonthlySaveFragment getInstance() {
		if(instance == null){
			instance = new InsertMonthlySaveFragment();
		}
		return instance;
	}

	private InsertMonthlySaveFragment() {}


	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_insert_monthly_save, container, false);
	}
}
