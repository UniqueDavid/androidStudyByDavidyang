package com.example.service;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void startService(View v){
		Intent intent=new Intent();
		intent.setClass(this, QuickStartService.class);
		startService(intent);
	}
	public void closeService(View v){
		Intent intent=new Intent();
		intent.setClass(this, QuickStartService.class);
		stopService(intent);
	}

}
