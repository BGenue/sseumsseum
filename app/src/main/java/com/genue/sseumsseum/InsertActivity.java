package com.genue.sseumsseum;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.w3c.dom.Text;

public class InsertActivity extends AppCompatActivity
{
	private String TAG = getClass().getSimpleName();

	Context mContext;
	ViewPager2 insertViewPager2;
	TabLayout insertTabLayout;

	int num_page = 4;
	int tabIndex = 0;//보여줄 위치
	int currentIndex = 0;

	Animation showVisibleAnim;
	Animation slideUpLine;
	Animation slideDownLine;
	Animation slideUpInsert;
	Animation slideDownInsert;

	ConstraintLayout insertLayout;//추가 레이아웃. 탭 위치마다 바뀜

	InsertPagerAdapter insertPagerAdapter;


	RecyclerView recyclerView;
	RelativeLayout line;

	boolean slideDownAniInit = false;
	boolean slideUpAniInit = false;

	UserDBManager userDBManager;


	TabLayout.OnTabSelectedListener mTabSelectedListener = new TabLayout.OnTabSelectedListener()
	{
		@Override
		public void onTabSelected(TabLayout.Tab tab)
		{
			if(recyclerView != null && Define.visible) {
				Define.visible = false;
//				recyclerView.clearAnimation();
//				line.clearAnimation();
				initUpSlideAni();
				recyclerView.startAnimation(slideUpInsert);
				line.startAnimation(slideUpLine);
			}
			currentIndex = tab.getPosition();
			Log.d(">>>>", "onTabSelected " + tab.getPosition());
		}

		@Override
		public void onTabUnselected(TabLayout.Tab tab)
		{

		}

		@Override
		public void onTabReselected(TabLayout.Tab tab)
		{

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(R.layout.activity_insert);

		userDBManager = UserDBManager.getInstance(mContext);
		tabIndex = getIntent().getIntExtra("tab", 10);
		tabIndex -= 10;


		showVisibleAnim = AnimationUtils.loadAnimation(mContext, R.anim.show_layout_anim);

		FloatingActionButton fab = findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				//showVisibleAnim 사용해
				if(!Define.visible) {
					Define.visible = true;
					initDownSlideAni();
					recyclerView.startAnimation(slideDownInsert);
					line.startAnimation(slideDownLine);
				}
				else {
					Define.visible = false;
					initUpSlideAni();
					recyclerView.startAnimation(slideUpInsert);
					line.startAnimation(slideUpLine);
				}
			}
		});

		insertViewPager2 = findViewById(R.id.insertViewPager2);
		insertPagerAdapter = new InsertPagerAdapter(this, num_page);
		insertViewPager2.setAdapter(insertPagerAdapter);
		insertViewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
		insertViewPager2.setOffscreenPageLimit(4);
		insertViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback()
		{
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
			{
				super.onPageScrolled(position, positionOffset, positionOffsetPixels);
				//				if(positionOffsetPixels == 0) {
				//					insertViewPager2.setCurrentItem(position);
				//				}
				//이동이 완료된 경우
				if(position == currentIndex) {
					Log.d(">>>>>", "currentIndex " + currentIndex);
					Fragment currentFragment = insertPagerAdapter.getCurrentFragment(currentIndex);
					insertLayout = currentFragment.getView().findViewById(R.id.insertLayout);
					recyclerView = currentFragment.getView().findViewById(R.id.recyclerView);
					line = currentFragment.getView().findViewById(R.id.line);
				}
			}

			@Override
			public void onPageSelected(int position)
			{
				super.onPageSelected(position);

			}


		});
		insertTabLayout = findViewById(R.id.tabInsertLayout);
		insertTabLayout.addOnTabSelectedListener(mTabSelectedListener);

		new TabLayoutMediator(insertTabLayout, insertViewPager2, new TabLayoutMediator.TabConfigurationStrategy()
		{
			@Override
			public void onConfigureTab(@NonNull TabLayout.Tab tab, int position)
			{
				if(position == 0) {
					tab.setText("워르급");
				}
				else if(position == 1) {
					tab.setText("정기 저축");
				}
				else if(position == 2) {
					tab.setText("정기 소비");
				}
				else {
					tab.setText("잔액");
				}
				if(position == tabIndex) {
					//					tab.select();
					//					insertViewPager2.setCurrentItem(tabIndex);
				}
			}
		}).attach();

		//		insertTabLayout.getTabAt(tabIndex).select();
		insertViewPager2.setCurrentItem(tabIndex);
	}

	public void initUpSlideAni(){
		slideUpLine = new TranslateAnimation(0, 0, insertLayout.getHeight(), -line.getHeight());
		slideUpLine.setDuration(500);
		slideUpLine.setFillAfter(true);
		slideUpInsert = new TranslateAnimation(0, 0, insertLayout.getHeight() + line.getHeight(), 0);
		slideUpInsert.setDuration(500);
		slideUpInsert.setFillAfter(true);
	}

	public void initDownSlideAni(){
		Log.d(TAG, "recyclerView.getHeight() " + recyclerView.getHeight());
		slideDownInsert = new TranslateAnimation(0, 0, 0, insertLayout.getHeight() + line.getHeight());
		slideDownInsert.setDuration(500);
		slideDownInsert.setFillAfter(true);
		slideDownInsert.setAnimationListener(new Animation.AnimationListener()
		{
			@Override
			public void onAnimationStart(Animation animation)
			{

			}

			@Override
			public void onAnimationEnd(Animation animation)
			{
				ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) recyclerView.getLayoutParams();
//				layoutParams.height = recyclerView.getHeight() - insertLayout.getHeight() - line.getHeight();
				Log.d(TAG, "layoutParams.height " + layoutParams.height + " recyclerView.getHeight() " + recyclerView.getHeight() +" insertLayout.getHeight() " + insertLayout.getHeight() + " line.getHeight() " + line.getHeight());
				layoutParams.
				recyclerView.setLayoutParams(layoutParams);
			}

			@Override
			public void onAnimationRepeat(Animation animation)
			{

			}
		});
		slideDownLine = new TranslateAnimation(0, 0, -line.getHeight(), insertLayout.getHeight());
		slideDownLine.setDuration(500);
		slideDownLine.setFillAfter(true);
	}
}
