package com.booksfloating.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.booksfloating.adapter.BookRecommendAdapter;
import com.booksfloating.domain.BooksRecommendBean;
import com.booksfloating.util.HttpUtil;
import com.xd.booksfloating.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class BooksRecommendFragment extends Fragment{
	private static String url = "www.baidu.com";
	private static String book_info = "{\"status\":\"1\",\"booklist\":" +
			"[{\"book\":\"java编程思想\",\"author\":\"Bruce Eckel\",\"picture\":\"https://img1.doubanio.com/lpic/s1320039.jpg\"}," +
			"{\"book\":\"我们仨\",\"author\":\"杨绛\",\"picture\":\"https://img1.doubanio.com/lpic/s1015872.jpg\"}," +
			"{\"book\":\"围城\",\"author\":\"钱钟书\",\"picture\":\"https://img1.doubanio.com/lpic/s1070222.jpg\"}]}";
	private static String urltest = "http://www.imooc.com/api/teacher?type=4&num=30";
	private ListView booksRecommendList = null;
	private List<BooksRecommendBean> booksBeanList;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.books_recommend, container, false);
		booksRecommendList = (ListView) view.findViewById(R.id.books_recommend_list);
		BooksRecommendAsyncTask booksRecommendAsyncTask = new BooksRecommendAsyncTask();
		booksRecommendAsyncTask.execute(urltest);
		return view;
	}
	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.books_recommend);
		booksRecommendList = (ListView)findViewById(R.id.books_recommend_list);
		BooksRecommendAsyncTask booksRecommendAsyncTask = new BooksRecommendAsyncTask();
		booksRecommendAsyncTask.execute(urltest);
	}*/
	class BooksRecommendAsyncTask extends AsyncTask<String, Void, List<BooksRecommendBean>>{

		@Override
		protected List<BooksRecommendBean> doInBackground(String... params) {
			String jsonData = HttpUtil.getJsonData(params[0]);
			return parseJsonData(jsonData);
		}
		@Override
		protected void onPostExecute(List<BooksRecommendBean> result) {
			super.onPostExecute(result);
			BookRecommendAdapter adapter = new BookRecommendAdapter(getActivity(), booksBeanList);
			booksRecommendList.setAdapter(adapter);
		}

		
	}
	private List<BooksRecommendBean> parseJsonData(String jsonData) {
		booksBeanList = new ArrayList<BooksRecommendBean>();
		try {
			
			JSONObject jsonObject = new JSONObject(jsonData);
			if(jsonObject.getString("status").equals("1")){
				
				/*JSONArray jsonArray = jsonObject.getJSONArray("bookList");
				for(int i = 0; i < jsonArray.length(); i++){
					jsonObject = jsonArray.getJSONObject(i);
					BooksRecommendBean booksRecommendBean = new BooksRecommendBean();
					booksRecommendBean.bookName = jsonObject.getString("book");
					booksRecommendBean.bookAuthor = jsonObject.getString("author");
					booksRecommendBean.bookImageUrl = jsonObject.getString("picture");
					booksRecommendBean.bookRanking = "No."+i;
					booksBeanList.add(booksRecommendBean);
					
					
				}*/
				JSONArray jsonArray = jsonObject.getJSONArray("data");
				for(int i = 0; i < jsonArray.length(); i++){
					jsonObject = jsonArray.getJSONObject(i);
					BooksRecommendBean booksRecommendBean = new BooksRecommendBean();
					booksRecommendBean.bookName = jsonObject.getString("name");
					booksRecommendBean.bookAuthor = jsonObject.getString("description");
					booksRecommendBean.bookImageUrl = jsonObject.getString("picSmall");
					
					booksRecommendBean.bookRanking = "No."+ (i+1);
					booksBeanList.add(booksRecommendBean);
					
				}
				
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return booksBeanList;
		
		
	}

}
