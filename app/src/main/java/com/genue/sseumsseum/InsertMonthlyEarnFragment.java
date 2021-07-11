package com.genue.sseumsseum;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InsertMonthlyEarnFragment extends Fragment
{
	private String TAG = getClass().getSimpleName();
	private static InsertMonthlyEarnFragment instance = null;

	UserDBManager userDBManager;

	ConstraintLayout insertLayout;
	RelativeLayout line;
	RecyclerView recyclerView;

	public static InsertMonthlyEarnFragment getInstance() {
		if(instance == null){
			instance = new InsertMonthlyEarnFragment();
		}
		return instance;
	}

	private InsertMonthlyEarnFragment() {}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
		userDBManager = UserDBManager.getInstance(getContext());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_insert_monthly_earn, container, false);
		insertLayout = v.findViewById(R.id.insertLayout);
		line = v.findViewById(R.id.line);
		recyclerView = v.findViewById(R.id.recyclerView);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		ArrayList<MonthlyIOInfo> lists = userDBManager.getEarnInfo();
		MonthlyEarnAdapter adapter = new MonthlyEarnAdapter(lists);
		recyclerView.setAdapter(adapter);

		v.findViewById(R.id.tvSave).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				boolean result = userDBManager.insertMonthlyEarn(1000, 2);
				Log.d(">>>>", "tvSave 클릭 " + result);
				userDBManager.showMonthlyEarn();
			}
		});
		v.findViewById(R.id.tvCancel).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Define.visible = false;
				Log.d(">>>>", "tvCancel 클릭");
				Animation slideUpLine = new TranslateAnimation(0, 0, insertLayout.getHeight(), -line.getHeight());
				slideUpLine.setDuration(500);
				slideUpLine.setFillAfter(true);
				Animation slideUpInsert = new TranslateAnimation(0, 0, insertLayout.getHeight() + line.getHeight(), 0);
				slideUpInsert.setDuration(500);
				slideUpInsert.setFillAfter(true);
				recyclerView.startAnimation(slideUpInsert);
				line.startAnimation(slideUpLine);
			}
		});

		return v;
	}
}
