package com.genue.sseumsseum;

import android.app.Activity;
import android.content.Intent;
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
import androidx.core.view.ViewCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.View;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
	UserDBManager userDB;

	UserInfo USER;

	ViewPager2 viewPager2;
	TabLayout tabLayout;

	TextView AccountText;

	public boolean isReady = false;

	public int num_page = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		Log.d(">>>>", "MainActivity onCreate");

		USER = UserInfo.getInstance();
		userDB = UserDBManager.getInstance(getApplicationContext());
		USER.setAccount(userDB.getAccount());
		if(USER.getAccount() == -1) {
			startActivityResult.launch(new Intent(this, StartActivity.class));
		}
		else {
			Log.d(">>>>", "시작금 : " + USER.getAccount());
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

		AccountText = findViewById(R.id.tvAccount);

		AccountText.setText(USER.getAccount() + " 원");

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

		viewPager2.setAdapter(new MainPagerAdapter(this, num_page));
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

			}
		});

		float pageMargin = this.getResources().getDimensionPixelOffset(R.dimen.margin);
		float pageOffset = this.getResources().getDimensionPixelOffset(R.dimen.offset);

		viewPager2.setPageTransformer(new ViewPager2.PageTransformer()
		{
			@Override
			public void transformPage(@NonNull View page, float position)
			{
				float myOffset = position * -(2 * pageOffset);
				if(viewPager2.getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL){
					if(ViewCompat.getLayoutDirection(viewPager2) == ViewCompat.LAYOUT_DIRECTION_RTL){
						page.setTranslationX(-myOffset);
					} else {
						page.setTranslationX(myOffset);
					}
				} else{
					page.setTranslationY(myOffset);
				}
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
				int money = fromOther.getIntExtra("money", -1);
				Log.d(">>>>", money+"");
				USER.setAccount(money);
				userDB.startData(money);
				initUI();
				isReady = true;
			}
		}
	});

	public void onInsertClick(View v){
		Intent insertIntent = new Intent(this, InsertActivity.class);
		switch(v.getId()){
			case R.id.tvEarn:
				insertIntent.putExtra("tab", Define.REQUEST_INSERT_EARN);
				break;
			case R.id.tvSave:
				insertIntent.putExtra("tab", Define.REQUEST_INSERT_SAVE);
				break;
			case R.id.tvSpend:
				insertIntent.putExtra("tab", Define.REQUEST_INSERT_SPEND);
				break;
			case R.id.tvAccount:
				insertIntent.putExtra("tab", Define.REQUEST_INSERT_ACCOUNT);
				break;
		}
		startActivityResult.launch(insertIntent);
	}
}