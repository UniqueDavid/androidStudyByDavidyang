package com.example.banker;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
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

	public void add(View v){
		//Intent intent=new Intent();
		//新建一个contentResolver，用于对于内容进行各种操作
		ContentResolver resolver=getContentResolver();
		
		//这里是固定的写法,这里com.yz.1234是authorities，后面跟的是matcher的暗号
		Uri url = Uri.parse("content://com.yz.1234/accounts");
		ContentValues values=new ContentValues();
		resolver.insert(url, values);
		
		
	}
}
