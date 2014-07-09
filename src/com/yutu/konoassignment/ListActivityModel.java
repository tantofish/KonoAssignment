package com.yutu.konoassignment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class ListActivityModel {
	JSONObject jObj;
	JSONParser jPar;
	JSONArray  jArr;
	ArrayList<Magazine> magList;
	
	
	// Constructor
	public ListActivityModel() {
		magList = new ArrayList<Magazine>();
	}
	
	public boolean grabJSONObjectFromWeb(String url){
		jPar = new JSONParser(url);
		jObj = jPar.getJSONObject();
		jArr = jPar.getJSONArray("magazines");
		parse();
		return true;
	}
	
	public void parse(){
		JSONObject obj;
		String coverURL;
		//https://d3xwm9x3ez74b.cloudfront.net/magcovers/53933893997a8-hq.jpg
		for(int i = 0 ; i < jArr.length() ; i++){
			Magazine mag = new Magazine();
			try {
				obj = jArr.getJSONObject(i);
				mag.setTitle(obj.getString("title"));
				mag.setBid(obj.getString("bid"));
				mag.setPublishDate(obj.getString("published_date"));
				mag.setIssue(obj.getString("issue"));
				mag.setFileType(obj.getString("file_type"));
				mag.setDescription(obj.getString("description"));
				mag.setIsNew(obj.getBoolean("is_new"));
				mag.setAvaiForSale(obj.getBoolean("available_for_sale"));
				mag.setHasPDF(obj.getBoolean("has_pdf"));
				mag.setHasFitReading(obj.getBoolean("has_fit_reading"));
				
				coverURL = "https://d3xwm9x3ez74b.cloudfront.net/magcovers/" + mag.getBid() + ".jpg";
				URL url = new URL(coverURL);
				Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
				mag.setCover(image);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Log.d("DEBUG", mag.toString());
			magList.add(mag);
			
		}
	}
	
	public class Magazine{
		private String title;
		private String bid;
		private String published_date;
		private String issue;
		private String file_type;
		private String description;
		private boolean is_new;
		private boolean available_for_sale;
		private boolean has_pdf;
		private boolean has_fit_reading;
		private Bitmap cover;
		
		public void setTitle(String str)		{	title				= str;	}
		public void setBid(String str)			{	bid					= str;	}
		public void setPublishDate(String str)	{	published_date		= str;	}
		public void setIssue(String str)		{	issue				= str;	}
		public void setFileType(String str)		{	file_type			= str;	}
		public void setDescription(String str)	{	description			= str;	}
		public void setIsNew(boolean bool)		{	is_new				= bool;	}
		public void setAvaiForSale(boolean bool){	available_for_sale	= bool;	}
		public void setHasPDF(boolean bool)		{	has_pdf				= bool;	}
		public void setHasFitReading(boolean bool){	has_fit_reading		= bool;	}
		public void setCover(Bitmap bmp)		{	cover				= bmp;	}
		
		public String getTitle()		{	return title;			}
		public String getBid()			{	return bid;				}
		public String getPublishDate()	{	return published_date;	}
		public String getIssue()		{	return issue;			}
		public String getFileType()		{	return file_type;		}
		public String getDescription()	{	return description;		}
		public boolean	isNew()			{	return is_new;			}
		public boolean	availableForSale(){	return available_for_sale;}
		public boolean	hasPDF()		{	return has_pdf;			}
		public boolean	hasFitReading()	{	return has_fit_reading;	}
		public Bitmap getCover()		{	return cover;			}
		
		public String toString(){
			String str = "title: " + title + "\n"
						+ "bid: " + bid + "\n"
						+ "published_date: " + published_date + "\n"
						+ "issue: " + issue + "\n"
						+ "file_type: " + file_type + "\n"
						+ "is_new: " + is_new + "\n"
						+ "available_for_sale: " + available_for_sale + "\n"
						+ "has_pdf: " + has_pdf + "\n"
						+ "has_fit_reading: " + has_fit_reading + "\n"
						+ "description: " + description + "\n";
			return str;
		}
	}
	public class JSONParser {
		JSONObject jObj;
		JSONArray  jArr;
		
		JSONParser(String URL) {
			
			/* Get the JSON data from the url, as String */
			Log.d("Requeted URL:", URL);
			StringBuilder sb = new StringBuilder();
			URLConnection urlConn = null;
			InputStreamReader in = null;
			
			try {
				URL mURL = new URL(URL);
				urlConn = mURL.openConnection();
				if (urlConn != null)
					urlConn.setReadTimeout(5 * 1000);
				if (urlConn != null && urlConn.getInputStream() != null) {
					in = new InputStreamReader(urlConn.getInputStream(),
							Charset.defaultCharset());
					BufferedReader bufferedReader = new BufferedReader(in);
					if (bufferedReader != null) {
						String line;
						while ((line = bufferedReader.readLine()) != null) {
							sb.append(line + "\n");
						}
						bufferedReader.close();
					}
				}
				in.close();
			} catch (Exception e) {
				throw new RuntimeException("Exception while calling URL:"
						+ URL, e);
			}
			
			/* Parse the JSON String to JSON Object */
			try {
				jObj = new JSONObject(sb.toString());
			} catch (JSONException e) {
				Log.e("JSON Parser", "Error parsing data " + e.toString());
			}
		}

		public JSONObject getJSONObject() {
			return jObj;
		}
		
		public JSONArray getJSONArray(String name){
			try {
				jArr = jObj.getJSONArray(name);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return jArr;
		}
	}
}
