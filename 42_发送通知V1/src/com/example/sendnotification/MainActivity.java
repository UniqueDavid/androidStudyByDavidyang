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
		//首先要拿到一个notificationManage的对象
		NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		//构建一个notification对象,链式调用方法串
		Notification noti1 = new Notification.Builder(this)
		         .setContentTitle("生日快乐 ")
		         .setContentText("今天是你的生日，我的宝贝")
		         .setSmallIcon(R.drawable.ic_launcher)
		         .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
		         .build();
		nm.notify(1, noti1);
		Notification noti2 = new Notification.Builder(this)
		         .setContentTitle("短信提示 ")
		         .setContentText("您收到10000万元，请查收")
		         .setSmallIcon(R.drawable.ic_launcher)
		         .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
		         .build();
		nm.notify(2, noti2);
	
	}

}
