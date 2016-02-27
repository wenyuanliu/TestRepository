package com.booksfloating.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.booksfloating.adapter.MyInfoPublishAdapter;
import com.booksfloating.domain.MyInfoPublishBookBean;
import com.xd.booksfloating.R;

public class MyInfoPublish extends BaseActionBarActivity{
	private static String url = "www.baidu.com";
	private static String urlTest = "http://www.imooc.com/api/teacher?type=4&num=30";
	private static String book_info = "{\"status\":\"1\",\"booklist\":" +
			"[{\"book\":\"java编程思想\",\"author\":\"Bruce Eckel\",\"university\":\"西安电子科技大学北校区\",\"publishTime\":\"2016年2月1日15时01分\",\"remark\":\"希望好心人尽快帮我借到！\",\"picture\":\"https://img1.doubanio.com/lpic/s1320039.jpg\"}," +
			"{\"book\":\"我们仨\",\"author\":\"杨绛\",\"university\":\"西安电子科技大学北校区\",\"publishTime\":\"2016年2月1日15时01分\",\"remark\":\"希望好心人尽快帮我借到！\",\"picture\":\"https://img1.doubanio.com/lpic/s1015872.jpg\"}," +
			"{\"book\":\"围城\",\"author\":\"钱钟书\",\"university\":\"西安电子科技大学北校区\",\"publishTime\":\"2016年2月1日15时01分\",\"remark\":\"希望好心人尽快帮我借到！\",\"picture\":\"https://img1.doubanio.com/lpic/s1070222.jpg\"}]}";
	
	private ListView myInfoBookPublishListView = null;
	private Button btn_myinfo_search_book = null;
	private List<MyInfoPublishBookBean> myPublishBookBeanList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myinfo_public_layout);
		getActionBar().setTitle("我的发布");
		
		
		Intent intent =getIntent();
		myInfoBookPublishListView= (ListView)findViewById(R.id.lv_my_info_book_publish);
		btn_myinfo_search_book = (Button) findViewById(R.id.btn_my_info_search_book);
		btn_myinfo_search_book.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Toast.makeText(MyInfoPublish.this, "预留搜索", Toast.LENGTH_SHORT).show();
			}
		});
		new MyInfoPublishAsyncTask().execute(urlTest);
		
		
		
	}
	
	
	private List<MyInfoPublishBookBean> getJsonData(String urlString) {
	
		try {
			URL url = new URL(urlString);
			InputStream is = url.openStream();
			String jsonData = readStream(is);
		    return parseJsonData(jsonData);
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	private List<MyInfoPublishBookBean> parseJsonData(String jsonData) {
		myPublishBookBeanList = new ArrayList<MyInfoPublishBookBean>();
		try {
			JSONObject jsonObject = new JSONObject(jsonData);
			if(jsonObject.getString("status").equals("1")){
				/*JSONArray jsonArray = jsonObject.getJSONArray("booklist");
				for(int i = 0; i<jsonArray.length(); i++){
					jsonObject = jsonArray.getJSONObject(i);
					MyInfoPublishBookBean publishBookBean = new MyInfoPublishBookBean();
					publishBookBean.bookName = jsonObject.getString("book");
					publishBookBean.bookAuthor = jsonObject.getString("author");
					publishBookBean.bookLocation = jsonObject.getString("university");
					publishBookBean.bookPublicshTime = jsonObject.getString("publishTime");
					publishBookBean.remark = jsonObject.getString("remark");
					publishBookBean.bookIconUrl =jsonObject.getString("picture");
					myPublishBookBeanList.add(publishBookBean);
					
				}*/
				
				
				
				JSONArray jsonArray = jsonObject.getJSONArray("data");
				for(int i = 0; i < jsonArray.length(); i++){
					jsonObject = jsonArray.getJSONObject(i);
					MyInfoPublishBookBean publishBookBean = new MyInfoPublishBookBean();
					publishBookBean.bookName = jsonObject.getString("name");
					publishBookBean.bookAuthor = jsonObject.getString("description");
					publishBookBean.bookIconUrl = jsonObject.getString("picSmall");
					
					myPublishBookBeanList.add(publishBookBean);
					
				}
				return myPublishBookBeanList;
				
			}
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	private String readStream(InputStream is) {
		
		
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
			
			StringBuilder result = new StringBuilder();
			String line = "";
			while((line = bufferedReader.readLine()) != null){
				result.append(line);
			}
			return result.toString();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	class MyInfoPublishAsyncTask extends AsyncTask<String, Void, List<MyInfoPublishBookBean>>{

		@Override
		protected List<MyInfoPublishBookBean> doInBackground(String... params) {
			
			return getJsonData(params[0]);
		}
		@Override
		protected void onPostExecute(List<MyInfoPublishBookBean> result) {
			super.onPostExecute(result);
			MyInfoPublishAdapter adapter = new MyInfoPublishAdapter(MyInfoPublish.this, myPublishBookBeanList);
			myInfoBookPublishListView.setAdapter(adapter);
		}

		
		
	}

	

}
