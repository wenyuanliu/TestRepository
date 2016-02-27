package com.booksfloating.activity;

import com.xd.booksfloating.R;

import android.app.Activity;
import android.os.Bundle;

public class MyInfoSetAbout extends BaseActionBarActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.myinfo_set_about_layout);
		getActionBar().setTitle("关于");
	}
}
