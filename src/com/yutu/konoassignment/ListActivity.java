package com.yutu.konoassignment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

public class ListActivity extends Activity{
	private ListActivityView mView;
	private ProgressDialog dialog;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		mView = new ListActivityView(this);
		
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
	    	 try {
	    		 Thread.sleep(5000);
	    	 } catch(InterruptedException ex) {
	    		 Thread.currentThread().interrupt();
	    	 }
	    	 return null;
	     }

	     protected void onPostExecute(Void results) {
	         //change view
	         //Intent myIntent = new Intent(ListActivity.this, ListActivity.class);
	         //ListActivity.this.startActivity(myIntent);

	         //kill the dialog waiting
	         dialog.dismiss();
	         dialog = null;
	     }
	 }
}
