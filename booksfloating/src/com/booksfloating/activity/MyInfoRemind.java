package com.booksfloating.activity;

import com.xd.booksfloating.R;


import android.app.Activity;
import android.os.Bundle;

public class MyInfoRemind extends BaseActionBarActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myinfo_remind_layout);
		getActionBar().setTitle("到期提醒");
		
		//是从本地订单中获取还是请求服务器；
	}
}
