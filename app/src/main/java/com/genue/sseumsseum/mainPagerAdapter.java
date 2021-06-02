package com.genue.sseumsseum;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class mainPagerAdapter extends FragmentStateAdapter
{
	int MAX_PAGES;

	public mainPagerAdapter(@NonNull FragmentActivity fragmentActivity, int count)
	{
		super(fragmentActivity);
		MAX_PAGES = count;
	}

	public mainPagerAdapter(@NonNull Fragment fragment)
	{
		super(fragment);
	}

	public mainPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle)
	{
		super(fragmentManager, lifecycle);
	}

	@NonNull
	@Override
	public Fragment createFragment(int position)
	{
		if(position == 0){
			return new DayFragment();
		} else if(position == 1){
			return new WeekFragment();
		} else if(position == 2){
			return new MonthFragment();
		}
		return null;
	}

	@Override
	public int getItemCount()
	{
		return MAX_PAGES;
	}


	public int realPosition(int pos){
		return pos % MAX_PAGES;
	}
}
