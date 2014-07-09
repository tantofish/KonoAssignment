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
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class ListActivity extends Activity{
	
	private ListActivityView mView;
	private ProgressDialog dialog;
	private ListActivityModel mModel;
	static String url = "http://yteam.thekono.com/KPI2/titles/gq/magazines";
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		mView  = new ListActivityView(this);
		mModel = new ListActivityModel();
		mView.setToContentView(this);
		doStuff();
	}
	

	void doStuff()
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
			dialog.dismiss();
			dialog = null;
		}
	}
}
