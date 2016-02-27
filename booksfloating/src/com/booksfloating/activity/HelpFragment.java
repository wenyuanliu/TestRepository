package com.booksfloating.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;


import android.widget.Toast;

import com.booksfloating.activity.MyInfoOrderHelpDetailActivity;
import com.booksfloating.adapter.MyInfoOrderAdapter;
import com.booksfloating.domain.BooksRecommendBean;
import com.booksfloating.util.HttpUtil;
import com.xd.booksfloating.R;

public class HelpFragment extends Fragment {
	
	private List<BooksRecommendBean> booksBeanList;
	private Button btn_myinfo_search_book = null;
	private ListView myInfoOrderListView = null;
	private static String urlTest = "http://www.imooc.com/api/teacher?type=4&num=30";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//View view = inflater.inflate(R.layout.myinfo_order_askfragment_test, container, false);
		View view = inflater.inflate(R.layout.myinfo_order_layout, container, false);
		myInfoOrderListView = (ListView)view.findViewById(R.id.lv_my_info_book_order);
		btn_myinfo_search_book.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Toast.makeText(getActivity(), "预留搜索", Toast.LENGTH_SHORT).show();
			}
		});
		MyInfoOrderAsyncTask myInfoOrderAsyncTask = new MyInfoOrderAsyncTask();
		myInfoOrderAsyncTask.execute(urlTest);
		myInfoOrderListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch(parent.getId()){
				case R.id.lv_my_info_book_order:
					IntentToActivity(position);
					break;
				}
				
			}
		}); 
		return view;
	}
	private void IntentToActivity(int position) {
		Intent intent = new Intent(getActivity(), MyInfoOrderHelpDetailActivity.class);
		intent.putExtra("position", position);
		startActivity(intent);
		
	}
	
	
	class MyInfoOrderAsyncTask extends AsyncTask<String, Void, List<BooksRecommendBean>>{

		@Override
		protected List<BooksRecommendBean> doInBackground(String... params) {
			String jsonData = HttpUtil.getJsonData(params[0]);
			
			return parseJsonData(jsonData);
		}
		@Override
		protected void onPostExecute(List<BooksRecommendBean> result) {
			super.onPostExecute(result);
			MyInfoOrderAdapter adapter = new MyInfoOrderAdapter(getActivity(), booksBeanList);
			myInfoOrderListView.setAdapter(adapter);
		}
		
	}
	public List<BooksRecommendBean> parseJsonData(String jsonData) {
		booksBeanList = new ArrayList<BooksRecommendBean>();
		try {
			
			JSONObject jsonObject = new JSONObject(jsonData);
			if(jsonObject.getString("status").equals("1")){
				/*
				JSONArray jsonArray = jsonObject.getJSONArray("bookList");
				for(int i = 0; i < jsonArray.length(); i++){
					jsonObject = jsonArray.getJSONObject(i);
					BooksRecommendBean booksRecommendBean = new BooksRecommendBean();
					booksRecommendBean.bookName = jsonObject.getString("book");
					booksRecommendBean.bookAuthor = jsonObject.getString("author");
					booksRecommendBean.bookLocation = jsonObject.getString("university");
					booksRecommendBean.bookPublicshTime = jsonObject.getString("publishTime");
					
					booksBeanList.add(booksRecommendBean);
					
					
				}*/
				JSONArray jsonArray = jsonObject.getJSONArray("data");
				for(int i = 0; i < jsonArray.length(); i++){
					jsonObject = jsonArray.getJSONObject(i);
					BooksRecommendBean booksRecommendBean = new BooksRecommendBean();
					booksRecommendBean.bookName = jsonObject.getString("name");
					booksRecommendBean.bookAuthor = jsonObject.getString("description");
					booksBeanList.add(booksRecommendBean);
					
				}
				
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return booksBeanList;
		
	}

}
