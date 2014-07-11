package com.yutu.konoassignment;
import itri.u9lab.towolf.ratiofixer.RatioRelativeLayout;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yutu.konoassignment.ListActivityModel.Magazine;

public class ListActivityView extends RatioRelativeLayout{
	ListView lv;
	Context mContext;
	ArrayList<Magazine> magList;
	
	public ListActivityView(Context context) {
		super(context);
		mContext = context;
		lv = new ListView(context);
		lv.setBackgroundColor(Color.WHITE);
		
		// Fill the ListView to RatioRelativeLayout
		this.addView(lv, 768, 1230, 0, 0);
	}
	

	public int setMagzines(ArrayList<Magazine> ml){
		magList = ml;
		lv.setAdapter(new MagazineAdapter(magList));
		return magList.size();
	}
	
	class MagazineAdapter extends BaseAdapter{
		ArrayList<Magazine> magList;
		MagazineAdapter(ArrayList<Magazine> ml){
			magList = ml;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return magList.size();
		}

		@Override
		public Magazine getItem(int position) {
			// TODO Auto-generated method stub
			return magList.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			if(convertView == null)
            {
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(R.layout.listviewcell, parent, false);
            }

            TextView magIssu = (TextView)convertView.findViewById(R.id.textView1);
            TextView magDesc = (TextView)convertView.findViewById(R.id.textView2);
            ImageView magImg = (ImageView)convertView.findViewById(R.id.imageView1);
            
            
            Magazine mag = magList.get(position);

            magIssu.setText(mag.getIssue());
            magDesc.setText(mag.getDescription());
            magImg.setImageBitmap(mag.getCover());
            
            return convertView;
		}
		
	}
}
