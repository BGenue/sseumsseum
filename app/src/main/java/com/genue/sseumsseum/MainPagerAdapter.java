package com.genue.sseumsseum;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MainPagerAdapter extends FragmentStateAdapter
{
	int MAX_PAGES;

	public MainPagerAdapter(@NonNull FragmentActivity fragmentActivity, int count)
	{
		super(fragmentActivity);
		MAX_PAGES = count;
	}

	@NonNull
	@Override
	public Fragment createFragment(int position)
	{
		int index = realPosition(position);
		if(index == 0) {
			return new DayFragment();
		}
		else if(index == 1) {
			return new WeekFragment();
		}
		else {
			return new MonthFragment();
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
}
