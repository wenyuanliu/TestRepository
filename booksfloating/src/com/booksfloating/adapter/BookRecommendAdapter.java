package com.booksfloating.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.booksfloating.domain.BooksRecommendBean;
import com.booksfloating.util.LoadBookImage;
import com.xd.booksfloating.R;

/**
 * 
 * @author sunhen
 *
 */
public class BookRecommendAdapter extends BaseAdapter{
	List<BooksRecommendBean> booksBeanList;

	LayoutInflater mLayoutInflater;
	

	public BookRecommendAdapter(Context context, List<BooksRecommendBean> booksBeanList) {
		super();
		this.booksBeanList = booksBeanList;
		this.mLayoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return booksBeanList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return booksBeanList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if(convertView == null){
			viewHolder = new ViewHolder();
			convertView = mLayoutInflater.inflate(R.layout.booklist_item, null);
			viewHolder.bookName = (TextView) convertView.findViewById(R.id.sh_book_name);
			viewHolder.bookAuthor = (TextView) convertView.findViewById(R.id.sh_book_author);
			viewHolder.bookImage = (ImageView) convertView.findViewById(R.id.sh_book_image);
			viewHolder.bookRanking = (TextView) convertView.findViewById(R.id.sh_book_ranking);
			convertView.setTag(viewHolder);
			
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
			
		}
		viewHolder.bookName.setText(booksBeanList.get(position).bookName);
		viewHolder.bookAuthor.setText(booksBeanList.get(position).bookAuthor);
		viewHolder.bookImage.setImageResource(android.R.id.icon);
		String url = booksBeanList.get(position).bookImageUrl;
		viewHolder.bookImage.setTag(url);
		new LoadBookImage().showImageByThread(viewHolder.bookImage, url);
		viewHolder.bookRanking.setText(booksBeanList.get(position).bookRanking);
		return convertView;
	}
	class ViewHolder{
		ImageView bookImage;
		TextView bookName;
		TextView bookAuthor;
		TextView bookRanking;
	}

}
