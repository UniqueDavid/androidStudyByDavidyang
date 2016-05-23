package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

public class QuickStartService extends Service {
	/*
	 * ����ĸ�����activity�ĸ���ĸ��� activity------�����û���������
	 * Service--------ʵ���Ͼ���һ��û�н����activity
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
					System.out.println("��������usb�ӿڵķ���...");
					SystemClock.sleep(2000);
					}
			};
		}.start();	
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		flag=true;
		System.out.println("�����յ��˿�����ָ��");
		return super.onStartCommand(intent, flags, startId);
		
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("����������");
		flag=false;
	}

}
