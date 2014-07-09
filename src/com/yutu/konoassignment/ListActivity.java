package com.yutu.konoassignment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class ListActivity extends Activity{
	
	private ListActivityView mView;
	private ProgressDialog dialog;
	private ListActivityModel mModel;
	static String url = "http://yteam.thekono.com/KPI2/titles/gq/magazines";
	Context mContext;
	ImageView iv;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		mContext = this;
		mView  = new ListActivityView(this);
		mModel = new ListActivityModel();
		
		loading();
		
		iv = new ImageView(this);
		
		mView.addView(iv, 400, 400, 0, 0);
		mView.setToContentView(this);
	}
	

	void loading()
	{
	    dialog = ProgressDialog.show(ListActivity.this, "", "Loading. Please wait...", true);
	    LoadMagazines lm = new LoadMagazines();
	    lm.execute();
	}

	private class LoadMagazines extends AsyncTask<Void, Void, Void> {
		
		protected Void doInBackground(Void... args) {
			
			mModel.grabJSONObjectFromWeb(url);
			
			return null;
		}

		protected void onPostExecute(Void results) {
			// change view
			// Intent myIntent = new Intent(ListActivity.this,
			// ListActivity.class);
			// ListActivity.this.startActivity(myIntent);

			// kill the waiting dialog 
			
			iv.setImageBitmap(mModel.magList.get(0).getCover());
			dialog.dismiss();
			dialog = null;
		}
	}
}
