package com.yz.duojiemian;

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
	public void gotosecondpage(View v){
		Intent intent=new Intent();
		//显示意图激活
//		intent.setClass(this,SecondActivity.class);
//		startActivity(intent);
		//隐式意图激活
		intent.putExtra("data","你好啊");
		intent.setAction("com.yz.xxx");
		intent.addCategory("android.intent.category.DEFAULT");
		startActivity(intent);
	}

}
