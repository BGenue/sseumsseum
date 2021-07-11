package com.genue.sseumsseum;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class InsertPagerAdapter extends FragmentStateAdapter
{
	int MAX_PAGES;

	InsertMonthlyEarnFragment insertMonthlyEarnFragment;
	InsertMonthlySaveFragment insertMonthlySaveFragment;
	InsertMonthlySpendFragment insertMonthlySpendFragment;
	InsertAccountFragment insertAccountFragment;

	public InsertPagerAdapter(@NonNull FragmentActivity fragmentActivity, int count)
	{
		super(fragmentActivity);
		MAX_PAGES = count;
		insertMonthlyEarnFragment = InsertMonthlyEarnFragment.getInstance();
		insertMonthlySaveFragment = InsertMonthlySaveFragment.getInstance();
		insertMonthlySpendFragment = InsertMonthlySpendFragment.getInstance();
		insertAccountFragment = InsertAccountFragment.getInstance();
	}

	@NonNull
	@Override
	public Fragment createFragment(int position)
	{
		int index = realPosition(position);
		if(index == 0) {
			return insertMonthlyEarnFragment;
		}
		else if(index == 1) {
			return insertMonthlySaveFragment;
		}
		else if(index == 2) {
			return insertMonthlySpendFragment;
		}
		else {
			return insertAccountFragment;
		}
	}

	@Override
	public int getItemCount()
	{
		return MAX_PAGES;
	}

	public int realPosition(int pos)
	{
		return pos%MAX_PAGES;
	}

	public Fragment getCurrentFragment(int index)
	{
		Log.d(">>>>", "getCurrentFragment " + index);
		if(index == 0) {
			return insertMonthlyEarnFragment;
		}
		else if(index == 1) {
			return insertMonthlySaveFragment;
		}
		else if(index == 2) {
			return insertMonthlySpendFragment;
		}
		else {
			return insertAccountFragment;
		}
	}
}
