package com.yz.duojiemian;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class SecondActivity extends Activity {
	private  TextView tv_info;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		tv_info=(TextView) findViewById(R.id.tv_info);
		Intent intent = getIntent();
		String data = intent.getStringExtra("data");
		tv_info.setText(data);
	}

}
