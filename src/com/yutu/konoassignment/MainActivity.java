package com.yutu.konoassignment;

import itri.u9lab.towolf.ratiofixer.*;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AnalogClock;
import android.widget.DigitalClock;
import android.widget.TextView;

public class MainActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		TextView tv = new TextView(this);
		AnalogClock ac = new AnalogClock(this);
		
		DigitalClock dc = new DigitalClock(this);
		
		tv.setText("Hello");
		
		setContentView(dc);
		
		
		RatioRelativeLayout rl = new RatioRelativeLayout(this);
	}

}
