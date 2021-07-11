package com.genue.sseumsseum;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.navigation.fragment.NavHostFragment;

public class StartActivity extends AppCompatActivity
{
	ConstraintLayout startLayout;
	TextView textview_first;
	TextView textview_second;
	EditText edit;
	Animation animation;
	Animation animation2;
	TextView button_first;
	Context mContext;

	Animation shakeAnim;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		mContext = this;
		init();
	}

	private void init(){
		shakeAnim = AnimationUtils.loadAnimation(mContext, R.anim.shake_view);
		startLayout = findViewById(R.id.startLayout);
		button_first = findViewById(R.id.button_first);
		edit = findViewById(R.id.edit);
		edit.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				switch (keyCode){
					case KeyEvent.KEYCODE_ENTER:
						if(edit.getText().length() > 0) {
							button_first.setBackground(ContextCompat.getDrawable(mContext, R.drawable.has_num_button_pressed_ripple));
							button_first.setTextColor(ContextCompat.getColor(mContext, R.color.white));
							button_first.setOnClickListener(new View.OnClickListener()
							{
								@Override
								public void onClick(View v)
								{
									String money = edit.getText().toString();
									if(money == null || money.length() == 0){
//										Animation shakeAnim = AnimationUtils.loadAnimation(mContext, R.anim.shake_view);
										startLayout.startAnimation(shakeAnim);
									}else{
										Intent toMain  = new Intent();
										toMain.putExtra("money", Integer.parseInt(money));
										setResult(Activity.RESULT_OK, toMain);
										finish();
									}
								}
							});
						}else{
							button_first.setBackground(ContextCompat.getDrawable(mContext, R.drawable.no_num_button_pressed_ripple));
							button_first.setTextColor(ContextCompat.getColor(mContext, R.color.white));
							startLayout.startAnimation(shakeAnim);
						}
				}
				return false;
			}
		});
		textview_first = findViewById(R.id.textview_first);
		textview_second = findViewById(R.id.textview_second);
		animation = AnimationUtils.loadAnimation(mContext, R.anim.text_appear);
		animation.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				textview_first.setVisibility(View.VISIBLE);
				textview_second.setVisibility(View.VISIBLE);
				textview_second.startAnimation(animation2);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}
		});
		animation2 = AnimationUtils.loadAnimation(mContext, R.anim.text_appear);
		textview_first.startAnimation(animation);

		button_first.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String money = edit.getText().toString();
				if(money == null || money.length() == 0){
					startLayout.startAnimation(shakeAnim);
				}
			}
		});
	}

	@Override
	public void onBackPressed()
	{
//		super.onBackPressed();
		//종료할지 물어보고 종료
	}
}
