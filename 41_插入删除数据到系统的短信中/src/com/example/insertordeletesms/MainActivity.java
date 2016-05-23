package com.example.insertordeletesms;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void insertsms(View v) {
		ContentResolver resolver=getContentResolver();
		
		Uri url=Uri.parse("content://sms");
		ContentValues values=new ContentValues();
		values.put("address","5201314");
		values.put("date",System.currentTimeMillis());
		values.put("type",1);
		values.put("body", "亲爱的，我想你了！！");
		resolver.insert(url, values);
	}

	public void deletesms(View v) {
		ContentResolver resolver=getContentResolver();
		
		Uri url=Uri.parse("content://sms");
		resolver.delete(url,"address=?", new String[]{"5201314"});
		Toast.makeText(this,"删除成功！！", 0).show();
	}
}
