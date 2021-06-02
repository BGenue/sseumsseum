package com.genue.sseumsseum;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
{
	UserDBManager userDB;

	ViewPager2 viewPager2;
	TabLayout tabLayout;

	public boolean isReady = false;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		Log.d(">>>>", "MainActivity onCreate");

		userDB = UserDBManager.getInstance(getApplicationContext());
		if(userDB.isEmpty()) {
			startActivityResult.launch(new Intent(this, StartActivity.class));
		}
		else {
			initUI();
		}
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		Log.d(">>>>", "MainActivity onResume");
	}

	private void initUI(){
		setContentView(R.layout.activity_main);

		FloatingActionButton fab = findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
			}
		});

		viewPager2 = findViewById(R.id.viewPager2);
		tabLayout = findViewById(R.id.tabLayout);

		viewPager2.setAdapter(new mainPagerAdapter(this, 3));
		viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
		viewPager2.setCurrentItem(0);
		viewPager2.setOffscreenPageLimit(3);
		viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback()
		{
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
			{
				super.onPageScrolled(position, positionOffset, positionOffsetPixels);
				if(positionOffsetPixels == 0) {
					viewPager2.setCurrentItem(position);
				}
			}

			@Override
			public void onPageSelected(int position)
			{
				super.onPageSelected(position);
				//인디케이터
			}
		});

		new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy()
		{
			@Override
			public void onConfigureTab(@NonNull TabLayout.Tab tab, int position)
			{

			}
		}).attach();
	}

//	@Override
//			public void onBackPressed(){
//		if(viewPager2.getCurrentItem() == 0){
//			super.onBackPressed();
//		} else {
//			viewPager2.setCurrentItem(viewPager2.getCurrentItem() - 1);
//		}
//	}

	ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
			new ActivityResultContracts.StartActivityForResult(),
			new ActivityResultCallback<ActivityResult>() {
		@Override
		public void onActivityResult(ActivityResult result)
		{
			Log.d(">>>>", "onActivityResult");
			if(result.getResultCode() == Activity.RESULT_OK) {
				Intent fromOther = result.getData();
				String money = fromOther.getStringExtra("money");
				Log.d(">>>>", money);
				initUI();
				isReady = true;
			}
		}
	});
}