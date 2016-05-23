package com.example.sendnotification;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void sendNotification(View v){
		//����Ҫ�õ�һ��notificationManage�Ķ���
		NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		//����һ��notification����,��ʽ���÷�����
		Notification noti1 = new Notification.Builder(this)
		         .setContentTitle("���տ��� ")
		         .setContentText("������������գ��ҵı���")
		         .setSmallIcon(R.drawable.ic_launcher)
		         .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
		         .build();
		nm.notify(1, noti1);
		Notification noti2 = new Notification.Builder(this)
		         .setContentTitle("������ʾ ")
		         .setContentText("���յ�10000��Ԫ�������")
		         .setSmallIcon(R.drawable.ic_launcher)
		         .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
		         .build();
		nm.notify(2, noti2);
	
	}

}
