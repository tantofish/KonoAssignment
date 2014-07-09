package com.yutu.konoassignment;

import itri.u9lab.towolf.ratiofixer.*;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity{
	
	Button startbtn;
	RatioRelativeLayout mView;
	int btn_W, btn_H;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);

		this.viewArrangement();

		mView.setToContentView(this);
	}

	void viewArrangement(){
		mView	 = new RatioRelativeLayout(this);
		mView.setBackgroundColor(Color.WHITE);
		startbtn = new Button(this);
		startbtn.setText("Start");
		startbtn.setOnClickListener(new startBtnClickListener());
		btn_W 	 = 300;
		btn_H	 = 150;
		mView.addView(startbtn, btn_W, btn_H, (768-btn_W)/2, (int)(1230/4)*3-btn_H/2);
	}
	
	
	class startBtnClickListener implements View.OnClickListener{
		Intent intent;
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			intent = new Intent();
			intent.setClass(MainActivity.this, ListActivity.class);
			startActivity(intent);
		}
	}
}
