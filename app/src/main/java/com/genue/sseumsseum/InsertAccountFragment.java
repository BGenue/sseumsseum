package com.genue.sseumsseum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class InsertAccountFragment extends Fragment
{
	private static InsertAccountFragment instance = null;

	public static InsertAccountFragment getInstance() {
		if(instance == null){
			instance = new InsertAccountFragment();
		}
		return instance;
	}

	private InsertAccountFragment() {}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_insert_account, container, false);
	}
}
