package com.example.remoteservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class RemoteService extends Service {
//����ע���Ϊaidl�ļ��Ժ�gen���Զ�����һ��stub�࣬��������໹���Զ��̳�Binder�࣬��˲���Ҫ�ټ̳�Binder
	private class MyAgent extends IService.Stub{

		@Override
		public void callMethodInService() {
			methodInService();
		}
		
	}
	@Override
	public IBinder onBind(Intent intent) {
		return new MyAgent();
	}
	public void methodInService(){
		System.out.println("���á�����35_Զ�̷�����á����������з���");
	}
	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println("���񴴽�");
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("��������");
	}
}
