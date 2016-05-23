package com.example.openbrowser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}
	public void enterBaidu(View v){
		Intent intent=new Intent();
		intent.setAction("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addCategory("android.intent.category.BROWSABLE");
		intent.setData(Uri.parse("https://www.baidu.com"));
		startActivity(intent);
	}
}
