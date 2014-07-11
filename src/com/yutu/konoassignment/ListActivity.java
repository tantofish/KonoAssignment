package com.yutu.konoassignment;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;


public class ListActivity extends Activity{
	
	private ListActivityView mView;
	private ProgressDialog dialog;
	private ListActivityModel mModel;
	private LoadMagazines lm;
	
	static String url = "http://yteam.thekono.com/KPI2/titles/gq/magazines";
	Context mContext;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		mContext = this;
		mView  = new ListActivityView(this);
		mModel = new ListActivityModel();
		Point size = new Point();
		getWindowManager().getDefaultDisplay().getSize(size);
		mModel.setScreenSize(size.y, size.x);
		Log.d("DEBUG", "Screen Size = ("+size.x+","+size.y+")");
		loading();
		
		
		mView.setToContentView(this);
	}
	

	void loading()
	{
	    dialog = ProgressDialog.show(ListActivity.this, "", "Loading. Please wait...", true);
	    lm = new LoadMagazines();
	    lm.execute();
	}

	private class LoadMagazines extends AsyncTask<Void, Void, Void> {
		
		protected Void doInBackground(Void... args) {
			
			mModel.grabJSONObjectFromWeb(url);
			
			return null;
		}

		protected void onPostExecute(Void results) {

			// kill the waiting dialog 
			mView.setMagzines(mModel.magList);
			dialog.dismiss();
			dialog = null;
		}
	}
}
