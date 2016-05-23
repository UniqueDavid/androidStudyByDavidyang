package com.example.sendnotification;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void sendNotification(View v){
		//这里用的是getsystemservice获得notificationmanager
		NotificationManager nm=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		Notification noti=new Notification(R.drawable.ic_launcher,"标题",System.currentTimeMillis());
		Intent intent=new Intent();
		intent.setAction(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel://110"));
		PendingIntent pendingIntent=PendingIntent.getActivity(this,1, intent, 1);
	
		noti.setLatestEventInfo(this,"给110打电话","由于调用了通知", pendingIntent);
		nm.notify(1, noti);
	}

}
