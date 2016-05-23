package com.yz.zhaungbishenqi;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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

	public void sendMsg(View v){
		new Thread(){
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				ContentResolver resolver=getContentResolver();
				
				Uri url=Uri.parse("content://sms");
				
				ContentValues values=new ContentValues();
				values.put("address","95533");
				values.put("date",System.currentTimeMillis());
				values.put("type","1");
				values.put("body", "��β��3356�Ĵ����˻�5��21��10ʱ15������֧�������800.00Ԫ���������82,900,000.00Ԫ��[��������]");
				resolver.insert(url, values);
				
				NotificationManager nm=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				
				Notification noti=new Notification(R.drawable.ic_launcher,"��β��3356�Ĵ����˻�5��21��10ʱ15������֧�������800.00Ԫ���������82,900,000.00Ԫ��[��������]",System.currentTimeMillis());
				
				Intent intent=new Intent();
				intent.setAction("android.intent.action.MAIN");
				intent.addCategory("android.intent.category.DEFAULT");
				intent.setType("vnd.android.cursor.dir/mms");
				PendingIntent pendingIntent=PendingIntent.getActivity(MainActivity.this, 1, intent, 1);
				noti.setLatestEventInfo(MainActivity.this,"���յ�һ������", "��β��3356�Ĵ����˻�5��21��10ʱ15������֧�������800.00Ԫ���������82,900,000.00Ԫ��[��������]",pendingIntent);
				
				nm.notify(1,noti);
				
				
				
			}
			
		}.start();
		Toast.makeText(MainActivity.this,"װ�Ƴɹ�����",0).show();
		
	}

}
