package com.example.remoteservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class RemoteService extends Service {
//这里注意改为aidl文件以后，gen会自动生成一个stub类，并且这个类还会自动继承Binder类，因此不需要再继承Binder
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
		System.out.println("调用》》》35_远程服务调用《《《服务中方法");
	}
	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println("服务创建");
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("服务销毁");
	}
}
