package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

public class QuickStartService extends Service {
	/*
	 * 服务的父类是activity的父类的父类 activity------是有用户界面的组件
	 * Service--------实际上就是一个没有界面的activity
	 */
	
	private static boolean flag=true;
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		new Thread(){
			
			public void run() {
				while(flag){
					System.out.println("启动监听usb接口的服务...");
					SystemClock.sleep(2000);
					}
			};
		}.start();	
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		flag=true;
		System.out.println("服务收到了开启的指令");
		return super.onStartCommand(intent, flags, startId);
		
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("服务销毁了");
		flag=false;
	}

}
