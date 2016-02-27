package com.booksfloating.activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.booksfloating.activity.AskFragment;
import com.booksfloating.activity.HelpFragment;
import com.xd.booksfloating.R;

public class MyInfoOrderFragmentActivity extends BaseActionBarFragmentActivity implements OnClickListener{

	private TextView tv_ask = null;
	private TextView tv_help = null;
	private Fragment askFragment = null;
	private Fragment helpFragment;
	private Fragment currentFragment;
	private FragmentManager fragmentManage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myinfo_order_tab);
		Intent intent = getIntent();
		
		
		getActionBar().setTitle("我的订单");
		
		
		fragmentManage = getFragmentManager();
		initView();
		initTab();
		
	}
	

	

	private void initView() {
		
		tv_ask = (TextView) findViewById(R.id.tv_my_info_order_ask);
		tv_help = (TextView) findViewById(R.id.tv_my_info_order_help);
		
		tv_ask.setOnClickListener(this);
		tv_help.setOnClickListener(this);
	}

	private void initTab() {
		
		
		if(askFragment == null){
			askFragment = new AskFragment();
		}
		if(!askFragment.isAdded()){
			FragmentTransaction transaction = fragmentManage.beginTransaction();
			transaction.add(R.id.fl_my_info_tab_content, askFragment);
			transaction.commit();
			currentFragment = askFragment;
			
			tv_ask.setTextColor(getResources().getColor(R.color.sh_blue));
			tv_help.setTextColor(getResources().getColor(R.color.sh_black));
		}
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.tv_my_info_order_ask:
			clickAskTab();
			break;
		case R.id.tv_my_info_order_help:
			clickHelpTab();
			break;
		default :
			break;
			
		}
		
	}


	private void clickAskTab() {
		if(askFragment == null){
			askFragment = new AskFragment();
		}
		addOrShowFragment(fragmentManage.beginTransaction(), askFragment);
		tv_ask.setTextColor(getResources().getColor(R.color.sh_blue));
		tv_help.setTextColor(getResources().getColor(R.color.sh_black));
		if(currentFragment == askFragment){
			return;
		}else{
			
		}
		
	}

	private void clickHelpTab() {
		if(helpFragment == null){
			helpFragment = new HelpFragment();
			
		}
		addOrShowFragment(fragmentManage.beginTransaction(), helpFragment);
		tv_help.setTextColor(getResources().getColor(R.color.sh_blue));
		tv_ask.setTextColor(getResources().getColor(R.color.sh_black));
	}
	private void addOrShowFragment(FragmentTransaction transaction,
			Fragment fragment) {
		if(currentFragment == fragment){
			return;
		}
		if(!fragment.isAdded()){
			transaction.add(R.id.fl_my_info_tab_content, fragment).commit();
		}else{
			transaction.hide(currentFragment).show(fragment).commit();
			
		}
		currentFragment = fragment;
		
	}
}
