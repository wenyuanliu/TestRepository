package com.booksfloating.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.booksfloating.domain.MyInfoPublishBookBean;
import com.booksfloating.util.LoadBookImage;
import com.xd.booksfloating.R;
/**
 * 
 * @author sunhen
 *
 */
public class MyInfoPublishAdapter extends BaseAdapter {
	List<MyInfoPublishBookBean> publishBookBeanList;
	LayoutInflater mLayoutInflater;

	

	public MyInfoPublishAdapter(Context context, List<MyInfoPublishBookBean> publishBookBeanList) {
		super();
		this.publishBookBeanList = publishBookBeanList;
		this.mLayoutInflater = LayoutInflater.from(context);
		
	}

	@Override
	public int getCount() {
		return publishBookBeanList.size();
	}

	@Override
	public Object getItem(int position) {
		return publishBookBeanList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if(convertView == null){
			viewHolder = new ViewHolder();
			convertView = mLayoutInflater.inflate(R.layout.myinfo_publish_item, null);
			
			viewHolder.bookName = (TextView) convertView.findViewById(R.id.tv_my_info_publish_book_name);
			viewHolder.bookAuthor = (TextView) convertView.findViewById(R.id.tv_my_info_publish_book_author);
			viewHolder.bookLocation = (TextView) convertView.findViewById(R.id.tv_my_info_publish_book_location);
			viewHolder.bookRemark = (TextView) convertView.findViewById(R.id.tv_my_info_publish_book_remark);
			viewHolder.bookImage = (ImageView) convertView.findViewById(R.id.iv_my_info_publish_book_image);
			convertView.setTag(viewHolder);
			
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
			
		}
		viewHolder.bookName.setText(publishBookBeanList.get(position).bookName);
		viewHolder.bookAuthor.setText(publishBookBeanList.get(position).bookAuthor);
		viewHolder.bookLocation.setText(publishBookBeanList.get(position).bookLocation);
		//时间问题
		//viewHolder.publishTime.setText(publishBookBeanList.get(position).bookPublicshTime);//后面添加
		viewHolder.bookRemark.setText(publishBookBeanList.get(position).bookRemark);
		viewHolder.bookImage.setImageResource(android.R.id.icon);
		String url = publishBookBeanList.get(position).bookIconUrl;
		viewHolder.bookImage.setTag(url);
		new LoadBookImage().showImageByThread(viewHolder.bookImage, url);
		return convertView;
	}
	class ViewHolder{
		ImageView bookImage;
		TextView bookName;
		TextView bookAuthor;
		TextView bookLocation;
		TextView publishTime;
		TextView bookRemark;
		
	}
	

}
