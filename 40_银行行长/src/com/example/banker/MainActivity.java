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
		//�½�һ��contentResolver�����ڶ������ݽ��и��ֲ���
		ContentResolver resolver=getContentResolver();
		
		//�����ǹ̶���д��,����com.yz.1234��authorities�����������matcher�İ���
		Uri url = Uri.parse("content://com.yz.1234/accounts");
		ContentValues values=new ContentValues();
		resolver.insert(url, values);
		
		
	}
}
